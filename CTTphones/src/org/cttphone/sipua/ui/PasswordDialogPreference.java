package org.cttphone.sipua.ui;

import org.cttphone.sipua.R;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.preference.PreferenceScreen;
import android.preference.Preference;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;

public class PasswordDialogPreference extends DialogPreference{
	private EditText oldPassEditText;
	private EditText newPassEditText;
	private EditText confirmPassEditText;
	
	private String oldText;
	private String newText;
	private String confirmText;
	Context mContext;
	
	//PasswordDialogPreference(Context context, AttributeSet attrs, int defStyle)
	//{
		//super(context, attrs, defStyle);
		/*
		oldPassEditText = new EditText(context, attrs);
		newPassEditText = new EditText(context, attrs);		
		confirmPassEditText = new EditText(context, attrs);
		
		oldPassEditText.setId(R.id.edit_old_password);
		newPassEditText.setId(R.id.edit_new_password);
		confirmPassEditText.setId(R.id.edit_ensure_password);
		
		oldPassEditText.setEnabled(true);
		newPassEditText.setEnabled(true);
		confirmPassEditText.setEnabled(true);
		*/
		
	//}

	public PasswordDialogPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		oldPassEditText = new EditText(context, attrs);
		newPassEditText = new EditText(context, attrs);		
		confirmPassEditText = new EditText(context, attrs);
		
		oldPassEditText.setId(R.id.edit_old_password);
		newPassEditText.setId(R.id.edit_new_password);
		confirmPassEditText.setId(R.id.edit_ensure_password);
		
		oldPassEditText.setEnabled(true);
		newPassEditText.setEnabled(true);
		confirmPassEditText.setEnabled(true);	
	}
	/*
	PasswordDialogPreference(Context context)
	{
		this(context, null);
	}*/
	
	/**
	 * {@inheritDoc}
	 */
	protected View onCreateDialogView() {
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());

		View view = layoutInflater.inflate(
			R.layout.modify_password, null);
	
	oldPassEditText = (EditText)view.findViewById(R.id.edit_old_password);			
	newPassEditText = (EditText)view.findViewById(R.id.edit_new_password);
	confirmPassEditText = (EditText)view.findViewById(R.id.edit_ensure_password);
	
   return view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void onClick(DialogInterface dialog, int which)
	{
		  mContext = getContext();
	  if (which == -1)
	      {
	    	     String strNewPassword = newPassEditText.getText().toString();
	    	     String strConfirmPassword = confirmPassEditText.getText().toString();
           String strOldPassword = oldPassEditText.getText().toString();
           String sharedPrefsFile = "org.cttphone.sipua_preferences";
           
	         SharedPreferences settings = mContext.getSharedPreferences(sharedPrefsFile, 0);
	         String old_admin_password = settings.getString("edit_old_password", "");
	         
	         if(old_admin_password.length() == 0)
	         				{
	        	    old_admin_password = "123456";
	         				}
	                	   
	         if (!strOldPassword.equals(old_admin_password))
	                       {
	        	 Toast.makeText(mContext, 
	        			 mContext.getString(R.string.admin__oldpassword_input), Toast.LENGTH_SHORT).show();
	        	               return;
	                       }       
	                     
	         if (!strNewPassword.equals(strConfirmPassword))
	                      {
	        	 Toast.makeText(mContext, 
	        			 mContext.getString(R.string.admin__password_confirm), Toast.LENGTH_SHORT).show();
	        	                    return;
	                      }
	         
	         Editor modify = settings.edit();
	         modify.putString("edit_old_password",strConfirmPassword);
	         modify.commit();
	         
	         Toast.makeText(mContext, 
        			 mContext.getString(R.string.admin__password_suc), Toast.LENGTH_SHORT).show();                    
	      }    	       
	}
	
}
