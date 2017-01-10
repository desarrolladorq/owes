package com.example.dev.owes.activeLists;




import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dev.owes.R;
import com.example.dev.owes.models.ShoppingList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Adds a new shopping list
 */

public class AddListDialogFragment extends DialogFragment {
    EditText mEditTextListName;
    /**
     * Public static constructor that creates fragment and
     * passes a bundle with data into it when adapter is created
     */
    public static AddListDialogFragment newInstance(){
        AddListDialogFragment addListDialogFragment = new AddListDialogFragment();
        Bundle bundle = new Bundle();
        addListDialogFragment.setArguments(bundle);
        return addListDialogFragment;
    }
    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the builder class for convenient dialog constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_list, null);
        mEditTextListName = (EditText) rootView.findViewById(R.id.edit_text_list_name);

        /**
         * Call addShoppingList() when user taps "Done" keyboard action
         */
        mEditTextListName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == keyEvent.ACTION_DOWN){
                    addShoppingList();
                }
                return true;
            }
        });
        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout */
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       addShoppingList();
                    }
                });




        return builder.create();

    }

    /**
     * Add new active list
     */

    public void addShoppingList(){
        // Get a reference to the firebase database
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        // Get the string the user entered in variable
        String userEnteredName = mEditTextListName.getText().toString();
        // Create an instance of Shopping List with the listName the user entered and an anonymous
        // owner
        ShoppingList shoppingList = new ShoppingList(userEnteredName, "nonymous owner");
        // set the child value in firebase, (we make an arbitrary node in order to update its child)
        rootRef.child("activeList").setValue(shoppingList);
    }
}




























