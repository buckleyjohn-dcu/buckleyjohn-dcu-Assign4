
/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.john.sdaprojectjohnbuckley;

import android.provider.BaseColumns;

 /**
  * API Contract for the Guest app.
  * Put definitions that are global to your whole database in the root level of the class
  * Citation:
  * Class contains code adapted from
  * URL:https://github.com/udacity/ud845-Pets
  * URL:https://developer.android.com/training/basics/data-storage/databases.html
  * Retrieved on 23th of April 2017
 * Created by John on 23/04/2017.
 */
public final class GuestContract {
    //

     /**
      * To prevent someone from accidentally instantiating the contract class, make the constructor private.
      * Code adapted from method PetContract() described at at https://github.com/udacity/ud845-Pets
      */
     private GuestContract() {}
    /* Inner class that defines the table contents
    * Class contains code adapted from
    * URL:https://github.com/udacity/ud845-Pets
    * URL:https://developer.android.com/training/basics/data-storage/databases.html
    * */
    public static class GuestFeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "guestListTable";
        public static final String GUEST_NAME = "guestName";
        public static final String GUEST_EMAIL = "email";
        public static final String GUEST_PHONENUMBER = "phonenumber";
    }

}
