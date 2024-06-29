/* ***************************************************************
* Autor............: Adryellen Alves de Souza
* Matricula........: 202110189
* Inicio...........: 22/06/2024
* Ultima alteracao.: 29/06/2024
* Nome.............: GroupManager
* Funcao...........: oEstrutura de dados que gerencia os grupos e os usuarios de cada grupo
*************************************************************** */

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

    // Metodo para listar todos os grupos
    public Set<String> listGroups() {
        return groups.keySet();
    }

    public Set<String> getGroupsForUser(String userName) {
        Set<String> userGroups = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : groups.entrySet()) {
            if (entry.getValue().contains(userName)) {
                userGroups.add(entry.getKey());
            }
        }
        return userGroups;
    }
}