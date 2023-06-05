import java.util.*;

public class Project {

    private int projectID;
    private int nTasks;
    private String projectName;
    private String projectDescription;
    private Map<Integer, List<Task>> tasks;
    private Map<String, Integer> nameToIdMap;

    Project(String name, String desc){
        tasks = new HashMap<>();
        nameToIdMap = new HashMap<>();
        this.nTasks = 0;
        this.projectName = name;
        this.projectDescription = desc;
    }

    // getTasks(): Mendapatkan daftar tugas dalam proyek
    // getTaskByID(taskID): Mendapatkan tugas berdasarkan ID
    // getTaskByName(taskName): Mendapatkan tugas berdasarkan nama
    // getTaskByPriority(priority): Mendapatkan daftar tugas berdasarkan prioritas
    // getTaskByWeight(weight): Mendapatkan daftar tugas berdasarkan bobot
    // getDependenciesOfTask(task): Mendapatkan daftar tugas dependensi dari tugas tertentu

    public void addTask(Task newTask){
        tasks.put(nTasks, new ArrayList<>());
        newTask.setTaskId(nTasks);
        tasks.get(nTasks).add(newTask);
        nameToIdMap.put(newTask.getTaskName(), nTasks++);
        //method anti syclic

        System.out.println("Task "+newTask.getTaskName()+" has been added to this project.");
        newTask.printDisplay();
    }

    public void addDependency(String taskName, String dependencyName){
        if(!nameToIdMap.containsKey(taskName) || !nameToIdMap.containsKey(dependencyName)){
            System.out.println("Task name or dependency name is not found.");
            return;
        }
        int taskId = getTaskIDByName(taskName);
        int dependencyId = getTaskIDByName(dependencyName);
        tasks.get(taskId).add(tasks.get(dependencyId).get(0));
    }

    public void deleteTask(String name){
        int id = getTaskIDByName(name);
        if(id == -1){
            System.out.println("Task name is not found.");
            return;
        }
        tasks.remove(id);
        nameToIdMap.remove(name);
        for(List<Task> edges: tasks.values()){
            edges.removeIf(e -> e.getTaskName().equals(name));
        }
        nTasks--;
    }

    public void deleteDependency(String taskName, String dependencyName){
        
    }

    public int getProjectId() {
        return this.projectID;
    }

    public int getTaskIDByName(String taskName) {
        return nameToIdMap.getOrDefault(taskName, -1);
    }

    public String getTaskNameByID(int taskID) {
        return tasks.get(taskID).get(0).getTaskName();
    }
    
    public int getProjectID() {
        return this.projectID;
    }

    public int getNumberOfTasks() {
        return this.nTasks;
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public String getProjectDescription() {
        return this.projectDescription;
    }
    
    public String getTasksName() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nProject Name: "+this.projectName+"\n");
        sb.append("List of Tasks: \n");
        for(int i=0;i<this.nTasks;i++){
            sb.append("Task "+(i+1)+": "+tasks.get(i).get(0).getTaskName()+"\n");
        }
        return sb.toString();
    }

    public Task getTaskByID(int taskID) {
        return tasks.get(taskID).get(0);
    }
    
    public Task getTaskByName(String taskName) {
        return tasks.get(getTaskIDByName(taskName)).get(0);
    }

    public Task[] getTaskByPriority(String priority) {
        return null;
    }

    public Task[] getTaskByWeight(int weight) {
        return null;
    }

    public Task[] getDependenciesOfTask(Task task) {
        return null;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    //shortest path method
    //dijkstra

    //mst?

    //disjoint set

    public List<Integer> topologicalSort(){
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int taskId : tasks.keySet()){
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

        for(Task neighbor : tasks.get(taskId)){
            int neighborId = neighbor.getTaskId();

            if(!visited.contains(neighborId)){
                dfs(neighborId, visited, stack);
            }
        }

        stack.push(taskId);
    }

    public String displayProjectDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project Name: "+this.projectName+"\n");
        sb.append("Project Description: "+this.projectDescription+"\n");
        sb.append("Total Tasks: "+this.nTasks+"\n");
        sb.append("Project Tasks: \n");
        for(int i=0;i<nTasks;i++){
            sb.append("Task "+(i+1)+":\n");
            sb.append(tasks.get(i).get(0).displayTaskDetail());
        }
        return sb.toString();
    }
}
