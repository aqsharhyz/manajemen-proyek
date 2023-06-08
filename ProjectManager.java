import java.util.*;

public class ProjectManager {

    public int nProjects;
    public Map<Integer, List<Project>> projects;
    public Map<String, Integer> nameToIdMap;

    ProjectManager() {
        projects = new HashMap<>();
        nameToIdMap = new HashMap<>();
        nProjects = 0;
    }

    public void addProject(Project newProject) {
        projects.put(nProjects, new ArrayList<>());
        newProject.setProjectID(nProjects);
        projects.get(nProjects).add(newProject);
        nameToIdMap.put(newProject.getProjectName(), nProjects++);
        System.out.println(newProject.displayProjectDetail());
    }

    public void addDependency(String projectName, String dependencyName) {
        if (!nameToIdMap.containsKey(projectName) || !nameToIdMap.containsKey(dependencyName)) {
            System.out.println("Project name or dependency name is not found.");
            return;
        }
        int projectId = nameToIdMap.get(projectName);
        int dependencyId = nameToIdMap.get(dependencyName);
        projects.get(projectId).add(projects.get(dependencyId).get(0));
    }

    public int getProjectIdByName(String projectName) {
        return nameToIdMap.getOrDefault(projectName, -1);
    }

    public String getProjectNameById(int projectId) {
        return projects.get(projectId).get(0).getProjectName();
    }

    public Project getProjectById(int projectId) {
        return projects.get(projectId).get(0);
    }

    public Project getProjectByName(String projectName) {
        return projects.get(getProjectIdByName(projectName)).get(0);
    }

    public String getListProjects() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of projects:\n");
        for (int i = 0; i < nProjects; i++) {
            Project temp = projects.get(i).get(0);
            sb.append((i+1) + ". " + temp.getProjectName() + "   " + temp.getProjectStatusAsString() + "\n");
            sb.append("   " + temp.getProjectDescription() + "\n\n");
        }
        return sb.toString();
    }

    public void deleteProject(int id) {
        if (!projects.containsKey(id)) {
            System.out.println("Task not found.");
            return;
        }
    
        int lastIndex = nProjects - 1;
        Project lastProject = projects.get(lastIndex).get(0);
        swapValues(projects, id, lastIndex);
    
        String projectName = projects.get(id).get(0).getProjectName();
        String lastProjectName = lastProject.getProjectName();
        swapValues(nameToIdMap, projectName, lastProjectName);
    
        projects.remove(lastIndex);
        nameToIdMap.remove(lastProjectName);
    
        for (List<Project> edges : projects.values()) {
            edges.removeIf(task -> task.getProjectId() == id);
        }

        nProjects--;
    }

    public void deleteDependency(String projectName, String dependencyName){
        if (!nameToIdMap.containsKey(projectName) || !nameToIdMap.containsKey(dependencyName)) {
            System.out.println("One or both projects not found.");
            return;
        }
        int projectId = nameToIdMap.get(projectName);
        int dependencyId = nameToIdMap.get(dependencyName);
        List<Project> dependencies = projects.get(projectId);
        dependencies.removeIf(task -> task.getProjectId() == dependencyId);
        System.out.println("Dependency removed.");
    }

    public List<Integer> topologicalSort(){
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int taskId : projects.keySet()){
            if(!visited.contains(taskId)){
                dfs(taskId, visited, stack);
            }
        }

        while(!stack.isEmpty()){
            result.add(stack.pop());
        }

        return result;
    }

    private void dfs(int taskId, Set<Integer> visited, Stack<Integer> stack){
        visited.add(taskId);

        for(Project neighbor : projects.get(taskId)){
            int neighborId = neighbor.getProjectId();

            if(!visited.contains(neighborId)){
                dfs(neighborId, visited, stack);
            }
        }

        stack.push(taskId);
    }

    private static <K, V> void swapValues(Map<K, V> map, K key1, K key2){
        V value1 = map.get(key1);
        V value2 = map.get(key2);
        map.remove(key1);
        map.remove(key2);
        map.put(key1, value2);
        map.put(key2, value1);
    }
}
