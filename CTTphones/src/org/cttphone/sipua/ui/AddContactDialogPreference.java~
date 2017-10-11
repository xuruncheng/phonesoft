package org.cttphone.sipua.ui;


import java.io.FileOutputStream;  
import java.io.InputStream; 
import java.io.FileInputStream;
import java.util.List; 
import java.util.ArrayList;
import java.io.File;
import android.os.Environment;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View;
import android.widget.Toast;
import android.preference.Preference;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;

import org.cttphone.sipua.R;
import org.cttphone.contact.*;



public class AddContactDialogPreference extends DialogPreference{
	private static final String TAG = "XML";  
    
    private ContactParse parser;// = new PullContactParse();  
    private List<ContactPerson> persons;
    private InputStream is;
    private File fileXml;
    private Context contextPref;

	public AddContactDialogPreference(Context context, AttributeSet attrs)
	{
		super(context, attrs);		   
	}
	
	protected View onCreateDialogView() {
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());

		View view = layoutInflater.inflate(
			R.layout.add_contact, null);
		
   return view;
	}
	
	public void onClick(DialogInterface dialog, int which)
	{
		if (which == -1)
	      {
				 contextPref = getContext();
			  File sdDir = null; 
			  String pathDir = "";
			   boolean sdCardExist = Environment.getExternalStorageState() 
					.equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在 
					if (sdCardExist) 
					{ 
						  sdDir = Environment.getExternalStorageDirectory();//获取跟目录 					
						  pathDir = sdDir.toString()+"//download//Contact.xml";
					} 
					
					try
					{
						fileXml = new File(pathDir);  
						is = new FileInputStream(fileXml); 
						
					}
					catch(Throwable e)
					{
						Toast.makeText(contextPref, R.string.admin_add_contact_error1, Toast.LENGTH_SHORT).show();
					}
					
					try
					{
						parser = new PullContactParse(); 
						persons = parser.parse(is);
						
					}
					catch(Throwable e)
					{
						Toast.makeText(contextPref, R.string.admin_add_contact_error2, Toast.LENGTH_SHORT).show();
						return;
					}
					
					try
					{
						ContactAddToPhone.CopyAllToPhone(persons, contextPref);
					}
					catch(Throwable e)
					{
						Toast.makeText(contextPref, R.string.admin_add_contact_error3, Toast.LENGTH_SHORT).show();
						return;
					}
				
					Toast.makeText(contextPref, R.string.admin_add_contact_suc, Toast.LENGTH_SHORT).show();
	      }
	}
	
}
