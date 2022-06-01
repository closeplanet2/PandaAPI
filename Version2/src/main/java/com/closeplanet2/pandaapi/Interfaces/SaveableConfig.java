package com.closeplanet2.pandaapi.Interfaces;

import com.closeplanet2.pandaapi.Objects.ServerConfig;

public interface SaveableConfig {
    String ReturnUUID();
    String ReturnDataType();
    void SaveData(ServerConfig serverConfig);
    void LoadData(ServerConfig serverConfig);
}
