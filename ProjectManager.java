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
            sb.append((i+1) + ". " + projects.get(i).get(0).getProjectName() + "   " + projects.get(i).get(0).getProjectStatusAsString() + "\n");
            sb.append("   " + projects.get(i).get(0).getProjectDescription() + "\n\n");
        }
        return sb.toString();
    }

    // public String projectProgress() {
    //     int completed = 0;
    //     for (int i = 0; i < nProjects; i++) {
    //         if (projects.get(i).get(0).getProjectStatus()) completed++;
    //     }
    //     return "Project progress: " + completed + "/" + nProjects + " projects completed.";
    // }

    public void deleteProject(int id){
        String name = getProjectNameById(id);
        if(id == -1){
            System.out.println("Project name is not found.");
            return;
        }
        projects.remove(id);
        nameToIdMap.remove(name);
        for(List<Project> edges : projects.values()){
            edges.removeIf(e -> e.getProjectID() == id);
        }
        nProjects--;
    }

    public void deleteDependency(String projectName, String dependencyName){
        
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
}
