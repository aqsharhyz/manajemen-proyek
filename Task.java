public class Task {

    private int taskWeight;
    private int taskPriority;
    private int taskId;
    private String taskName;
    private String taskDescription;
    private boolean taskStatus;
    //task dependencies
    //private int taskDuration;
    //apakah perlu weight antar jarak?

    Task(String name, String desc, int weight, int priority, boolean completed){
        this.taskName = name;
        this.taskDescription = desc;
        this.taskWeight = weight;
        this.taskPriority = priority;
        this.taskStatus = completed;
    }



    // Methods:
    // getDependencies(): Mendapatkan daftar tugas dependensi
    // isDependentOn(task): Memeriksa apakah tugas ini memiliki dependensi pada tugas tertentu

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public int getTaskWeight() {
        return taskWeight;
    }

    public int getTaskPriority() {
       return taskPriority;
    }

    public String getTaskStatus() {
        if(this.taskStatus) return "Completed";
        return "Not completed";
    }

    public String getPriorityAsString() {
        switch(this.taskPriority){
            case 5:
                return "Super important";
            case 4:
                return "Very important";
            case 3:
                return "Important";
            case 2:
                return "Quite important";
            case 1:
                return "Less important";
            default:
                return "Unknown";
        }
    }

    public int getPriorityAsInt(String priority) {
        switch(priority){
            case "Super important":
                return 5;
            case "Very important":
                return 4;
            case "Important":
                return 3;
            case "Quite important":
                return 2;
            case "Less important":
                return 1;
            default:
                return 0;
        }
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskWeight(int taskWeight) {
        this.taskWeight = taskWeight;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void addDependency(){}

    public void removeDependency(){}

    public String displayTaskDetail() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Task ID: "+this.taskID+"\n");
        sb.append("Task name: "+this.taskName+"\n");
        sb.append("Task description: "+this.taskDescription+"\n");
        sb.append("Task weight: "+this.taskWeight+"\n");
        sb.append("Task priority: "+this.getPriorityAsString()+"\n");
        sb.append("Task status: "+this.getTaskStatus()+"\n");
        //sb.append("Task dependencies: "+this.getDependencies()+"\n");
        return sb.toString();
    }

    public void printDisplay() {
        System.out.println(this.displayTaskDetail());
    }
}