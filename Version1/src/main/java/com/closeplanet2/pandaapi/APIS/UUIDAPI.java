package com.closeplanet2.pandaapi.APIS;

import java.util.UUID;

public class UUIDAPI {
    public static boolean IsUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
