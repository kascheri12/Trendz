package com.trendzcatalog.trendz.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.ServiceGenerator;
import com.trendzcatalog.trendz.models.Closet;
import com.trendzcatalog.trendz.models.UserInfo;
import com.trendzcatalog.trendz.services.ClosetService;
import com.trendzcatalog.trendz.services.LoginService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private UserCreateTask mCreateTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View mRegisterView;
    private boolean mIsUserExists;

    private UserInfo mUserInfo;
    private int mClosetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailView.setBackgroundResource(R.drawable.tags_rounded_corners);
        mEmailView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on Enter key press
                    mEmailView.clearFocus();
                    mPasswordView.requestFocus();
                    return true;
                }
                return false;
            }
        });


        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    attemptLogin(false);
                    return true;
                }
                return false;
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin(false);
                    return true;
                }
                return false;
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mRegisterView = (TextView) findViewById(R.id.textViewRegister);
        mRegisterView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(true);
            }
        });

        if (isSavedLoginInfo()) {
            mEmailView.setText(mUserInfo.getUsername());
            mPasswordView.setText(mUserInfo.getPassword());
            attemptLogin(false);
        }


    }

    private boolean isSavedLoginInfo() {
        mUserInfo = getSavedLoginInfo();
        if (mUserInfo.getUserInfoID() > 0) {
            return true;
        }
        return false;
    }

    private void clearSavedLoginInfo() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
        edit.commit();
    }

    private UserInfo getSavedLoginInfo() {
        UserInfo user = new UserInfo();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("UserInfo", 0);
        user.setUserInfoID(pref.getInt("UserInfoID", 0));
        user.setUsername(pref.getString("Username", null));
        user.setPassword(pref.getString("Password", null));
        return user;
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin(boolean register) {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            clearSavedLoginInfo();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            if (register) {
                mCreateTask = new UserCreateTask(email, password);
                mCreateTask.execute((Void) null);
            } else {
                mAuthTask = new UserLoginTask(email, password);
                mAuthTask.execute((Void) null);
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }



    public class UserCreateTask extends AsyncTask<Void, Void, Boolean> {
        private UserInfo mUserInfo;

        UserCreateTask(String email, String password) {
            mUserInfo = new UserInfo(0, email, password);
        }

        private void setSavedLoginInfo(UserInfo mUserInfo, int ClosetID) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("UserInfoID", mUserInfo.getUserInfoID());
            edit.putString("Username", mUserInfo.getUsername());
            edit.putString("Password", mUserInfo.getPassword());
            edit.putInt("ClosetID", ClosetID);
            edit.commit();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            LoginService loginService = ServiceGenerator.createService(LoginService.class);
            ClosetService closetService = ServiceGenerator.createService(ClosetService.class);
            Call<UserInfo> call = loginService.Create(this.mUserInfo.getUsername(), this.mUserInfo.getPassword());
            try {
                Response<UserInfo> user = call.execute();
                if (user.body().getUserInfoID() > 0) {
                    this.mUserInfo.setUserInfoID(user.body().getUserInfoID());
                    Call<Closet> call1 = closetService.GetClosetByUserID(this.mUserInfo.getUserInfoID());
                    Response<Closet> closet = call1.execute();
                    if (closet.body().ClosetID > 0) {
                        mClosetID = closet.body().ClosetID;
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.toString();
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (!success) {
                mEmailView.setError(getString(R.string.error_username_exists));
                mEmailView.requestFocus();
            } else {
                setSavedLoginInfo(mUserInfo, mClosetID);
                finish();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private UserInfo mUserInfo;
        private int mClosetID;

        UserLoginTask(String email, String password) {
            this.mUserInfo = new UserInfo(0, email, password);
        }

        private void setSavedLoginInfo(UserInfo mUserInfo, int ClosetID) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("UserInfoID", mUserInfo.getUserInfoID());
            edit.putString("Username", mUserInfo.getUsername());
            edit.putString("Password", mUserInfo.getPassword());
            edit.putInt("ClosetID", ClosetID);
            edit.commit();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            LoginService loginService = ServiceGenerator.createService(LoginService.class);
            ClosetService closetService = ServiceGenerator.createService(ClosetService.class);
            Call<UserInfo> call = loginService.Validate(this.mUserInfo.getUsername(), this.mUserInfo.getPassword());
            try {
                Response<UserInfo> user = call.execute();
                if (user.body().getUsername() != "") {
                    this.mUserInfo.setUserInfoID(user.body().getUserInfoID());
                    Call<Closet> call1 = closetService.GetClosetByUserID(mUserInfo.getUserInfoID());
                    Response<Closet> closet = call1.execute();
                    if (closet.body().ClosetID > 0) {
                        mClosetID = closet.body().ClosetID;
                        return true;
                    }
                }
            } catch (Exception ex) {
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (!success) {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            } else {
                setSavedLoginInfo(mUserInfo, mClosetID);
                finish();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

