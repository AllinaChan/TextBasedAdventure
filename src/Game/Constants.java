package Game;

public class Constants {

    public static int keyID=-1;
    public static int RoomID=-1;
    public static int lockID=-1;
    public static int fragmentID=0;

//All the following methods, just increments the IDs by one

    public static String getNextKeyID()
    {
        keyID++;
        return Integer.toString(keyID);
    }

    public static String getNextRoomID()
    {
        RoomID++;
        return Integer.toString(RoomID);
    }
    public static String getNextLockID()
    {
        lockID++;
        return Integer.toString(lockID);
    }
    public static String getNextFragmentID()
    {
        lockID++;
        return Integer.toString(fragmentID);
    }


}
