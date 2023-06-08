public class Task {

    private int taskWeight;
    private int taskPriority;
    private int taskId;
    private String taskName;
    private String taskDescription;
    private boolean taskStatus;

    Task(String name, String desc, int weight, int priority, boolean completed){
        this.taskName = name;
        this.taskDescription = desc;
        this.taskWeight = weight;
        this.taskPriority = priority;
        this.taskStatus = completed;
    }

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

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public String getTaskStatusAsString() {
        return this.taskStatus? "Completed" : "Not completed";
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

    public String displayTaskDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task name: "+this.taskName+"\n");
        sb.append("Task description: "+this.taskDescription+"\n");
        sb.append("Task weight: "+this.taskWeight+"\n");
        sb.append("Task priority: "+this.getPriorityAsString()+"\n");
        sb.append("Task status: "+this.getTaskStatusAsString()+"\n");
        return sb.toString();
    }
}