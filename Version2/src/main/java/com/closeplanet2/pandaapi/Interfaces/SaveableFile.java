package com.closeplanet2.pandaapi.Interfaces;

import com.closeplanet2.pandaapi.Objects.ServerConfig;

public interface SaveableFile {
    void SaveData(ServerConfig serverConfig, String path);
    void LoadData(ServerConfig serverConfig, String path);
}
