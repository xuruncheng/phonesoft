package org.cttphone.contact;

import android.os.RemoteException;
import android.os.OperationCanceledException;
import android.content.ContentProviderResult;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentProviderOperation;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.util.Log;
import android.content.OperationApplicationException;

public class ContactAddToPhone{
	
	public static void CopyAllToPhone(List<ContactPerson> list,Context ctx) throws Throwable{
	    //文档位置：reference\android\provider\ContactsContract.RawContacts.html
	    ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		
		int rawContactInsertIndex = 0;
	    
	 for (int i = 0; i < list.size(); i++)
	    {
		   
		  rawContactInsertIndex = ops.size();
	    ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
	            .withValue(RawContacts.ACCOUNT_TYPE, null)
	            .withValue(RawContacts.ACCOUNT_NAME, null)
	            .build());
	    
	    //文档位置：reference\android\provider\ContactsContract.Data.html
	    ops.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI)
	            .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
	            .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
	            .withValue(StructuredName.GIVEN_NAME, list.get(i).getPersonName())
	            .build());
	    
	    ops.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI)
	             .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
	             .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
	             .withValue(Phone.NUMBER, list.get(i).getPhoneNumber())
	             .withValue(Phone.TYPE, Phone.TYPE_MOBILE)
	             .withValue(Phone.LABEL, "")
	             .build());
	    
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE,
                         ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, list.get(i).getHomeNumber())
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, Phone.TYPE_HOME)
                .build());
	    
	    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE,
                     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, list.get(i).getVoipNumber())
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, Phone.TYPE_WORK)
                .build());
	      
	      
	      ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI) 
                  .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex) 
                  .withValue(ContactsContract.Data.MIMETYPE, 
                          ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE) 
                  .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, list.get(i).getCompany()) 
                  .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, 
                          ContactsContract.CommonDataKinds.Organization.TYPE_WORK) 
                  .build()); 
	    
	    ops.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI)
	             .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
	             .withValue(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
	             .withValue(Email.DATA, list.get(i).getEmail())
	             .withValue(Email.TYPE, Email.TYPE_WORK).withYieldAllowed(true)
	             .build());
	    }
	    
	    ctx.getContentResolver().applyBatch(ContactsContract.AUTHORITY,ops);
	    
	}

}
