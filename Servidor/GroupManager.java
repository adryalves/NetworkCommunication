package Servidor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Classe para gerenciar os grupos e os usuario de cada grupo
public class GroupManager {

    private Map<String, Set<String>> groups;

    public GroupManager() {
        groups = new HashMap<>();
    }

    public void addGroup(String groupName) {
        groups.putIfAbsent(groupName, new HashSet<>());
    }

    public void addUserToGroup(String groupName, String userName) {
        groups.computeIfAbsent(groupName, k -> new HashSet<>()).add(userName);
    }

    public void removeUserFromGroup(String groupName, String userName) {
        Set<String> users = groups.get(groupName);
        if (users != null) {
            users.remove(userName);
            if (users.isEmpty()) {
                groups.remove(groupName);
            }
        }
    }

    public Set<String> getUsersInGroup(String groupName) {
        return groups.getOrDefault(groupName, new HashSet<>());
    }

    public void removeGroup(String groupName) {
        groups.remove(groupName);
    }

    // Método para listar todos os grupos
    public Set<String> listGroups() {
        return groups.keySet();
    }
}