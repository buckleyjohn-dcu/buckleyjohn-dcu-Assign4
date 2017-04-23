package com.example.john.sdaprojectjohnbuckley;

import android.provider.BaseColumns;

/**
 * Created by John on 23/04/2017.
 */
public final class GuestContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private GuestContract() {}
    /* Inner class that defines the table contents */
    public static class GuestFeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "guestListTable";
        public static final String GUEST_NAME = "guestName";
        public static final String GUEST_EMAIL = "email";
        public static final String GUEST_PHONENUMBER = "phonenumber";
    }

}
