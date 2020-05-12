package DATABASE;

import android.provider.BaseColumns;

public final class UsersMaster {
    public UsersMaster() {
    }

    public static class Users implements BaseColumns {
        public static final String Table_Name = "users";
        public static final String Column_Name_UserName  = "username";
        public static final String Column_Name_Password = "password";
    }
}
