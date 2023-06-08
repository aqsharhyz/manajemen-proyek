import java.util.*;

public class Project {

    private int projectID;
    private int nTasks;
    private int totalProjectWeight;
    private String projectName;
    private String projectDescription;
    private boolean projectStatus;
    private Map<Integer, List<Task>> tasks;
    private Map<String, Integer> nameToIdMap;

    Project(String name, String desc){
        tasks = new HashMap<>();
        nameToIdMap = new HashMap<>();
        this.nTasks = 0;
        this.totalProjectWeight = 0;
        this.projectName = name;
        this.projectDescription = desc;
    }

    public void addTask(Task newTask){
        tasks.put(nTasks, new ArrayList<>());
        newTask.setTaskId(nTasks);
        tasks.get(nTasks).add(newTask);
        nameToIdMap.put(newTask.getTaskName(), nTasks++);
        checkProjectStatus();
        totalProjectWeight += newTask.getTaskWeight();
        System.out.println("Task "+newTask.getTaskName()+" has been added to this project.");
        System.out.println(newTask.displayTaskDetail());
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

    public void deleteTask(int id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Task not found.");
            return;
        }
    
        int lastIndex = nTasks - 1;
        Task lastTask = tasks.get(lastIndex).get(0);
    
        totalProjectWeight -= tasks.get(id).get(0).getTaskWeight();
    
        swapValues(tasks, id, lastIndex);
    
        String taskName = tasks.get(id).get(0).getTaskName();
        String lastTaskName = lastTask.getTaskName();
        swapValues(nameToIdMap, taskName, lastTaskName);
    
        tasks.remove(lastIndex);
        nameToIdMap.remove(lastTaskName);
    
        for (List<Task> edges : tasks.values()) {
            edges.removeIf(task -> task.getTaskId() == id);
        }
    
        checkProjectStatus();
        nTasks--;
    }
    

    public void deleteDependency(String taskName, String dependencyName) {
        if (!nameToIdMap.containsKey(taskName) || !nameToIdMap.containsKey(dependencyName)) {
            System.out.println("One or both tasks not found.");
            return;
        }
        int taskId = nameToIdMap.get(taskName);
        int dependencyId = nameToIdMap.get(dependencyName);
        List<Task> dependencies = tasks.get(taskId);
        dependencies.removeIf(task -> task.getTaskId() == dependencyId);
        System.out.println("Dependency removed.");
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

    public boolean getProjectStatus() {
        return this.projectStatus;
    }

    public String getProjectStatusAsString() {
        return (this.projectStatus) ? "Completed" : "Not completed";
    }

    public int getTotalProjectWeight() {
        return totalProjectWeight;
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

    public Task[] getTaskByWeight(int weight) {
        return null;
    }

    public Set<List<Task>> getTaskByPriority(int priority) {
        Set<List<Task>> result = new HashSet<>();
        for(int i=0;i<nTasks;i++){
            if(tasks.get(i).get(0).getTaskPriority() == priority){
                result.add(tasks.get(i));
            }
        }
        return result;
    }

    public ArrayList<Task> sortTaskByWeight() {
        ArrayList<Task> temp = new ArrayList<>();
        for(int i=0;i<nTasks;i++){
            temp.add(tasks.get(i).get(0));
        }
        Collections.sort(temp, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t2.getTaskWeight() - t1.getTaskWeight();
            }
        });
        return temp;
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

    public void checkProjectStatus() {
        for(int i=0;i<nTasks;i++){
            if(!tasks.get(i).get(0).getTaskStatus()){
                this.projectStatus = false;
                return;
            }
        }
        this.projectStatus = true;
    }

    public String projectProgress() {
        int completed = 0;
        for (int i = 0; i < nTasks; i++) {
            if (tasks.get(i).get(0).getTaskStatus()) completed++;
        }
        return "Project progress: " + completed + "/" + nTasks + " task completed.";
    }

    public String displayProjectDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nProject Name: "+this.projectName+"\n");
        sb.append("Project Description: "+this.projectDescription+"\n");
        sb.append("Total Tasks: "+this.nTasks+"\n");
        sb.append("Project Status: "+this.getProjectStatusAsString()+"\n");
        sb.append("Project Tasks: \n");
        for(int i=0;i<nTasks;i++){
            sb.append("\nTask "+(i+1)+":\n");
            sb.append(tasks.get(i).get(0).displayTaskDetail());
        }
        return sb.toString();
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
