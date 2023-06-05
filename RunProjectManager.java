import java.util.*;

public class RunProjectManager {

    public static void printMainMenu(Scanner sc) {
        // Membuat objek proyek
        ProjectManager pm = new ProjectManager();

        // Menambahkan proyek ke dalam manajer proyek
        pm.addProject(new Project("Pengembangan Aplikasi Manajemen Proyek Kolaboratif", "helo"));

        // Mendapatkan objek proyek berdasarkan nama
        Project project = pm.getProjectByName("Pengembangan Aplikasi Manajemen Proyek Kolaboratif");

        // Menambahkan tugas-tugas ke dalam proyek
        project.addTask(new Task("Analisis Kebutuhan Pengguna", "Melakukan analisis kebutuhan pengguna untuk mengidentifikasi fitur-fitur yang diperlukan dalam aplikasi", 1, 1, false));
        project.addTask(new Task("Desain Antarmuka Pengguna (UI/UX)", "Merancang antarmuka pengguna yang intuitif dan menarik untuk aplikasi", 1, 1, false));
        project.addTask(new Task("Pengembangan Backend", "Mengembangkan logika bisnis dan API backend untuk mengelola data proyek dan tugas", 1, 1, false));
        project.addTask(new Task("Pengembangan Frontend", "Membangun antarmuka pengguna interaktif menggunakan HTML, CSS, dan JavaScript", 1, 1, false));
        project.addTask(new Task("Pengujian dan Debugging", "Melakukan pengujian perangkat lunak, menemukan dan memperbaiki bug, serta memastikan aplikasi berjalan dengan baik", 1, 1, false));
        project.addTask(new Task("Implementasi Fitur Manajemen Tugas", "Mengimplementasikan fitur-fitur manajemen tugas seperti pembuatan, penugasan, pemantauan, dan penyelesaian tugas", 1, 1, false));
        project.addTask(new Task("Integrasi dengan Alat Kolaborasi", "Mengintegrasikan aplikasi dengan alat kolaborasi eksternal seperti Slack atau Microsoft Teams", 1, 1, false));

        project.addDependency("Desain Antarmuka Pengguna (UI/UX)", "Analisis Kebutuhan Pengguna");
        project.addDependency("Pengembangan Backend", "Desain Antarmuka Pengguna (UI/UX)");
        project.addDependency("Pengembangan Frontend", "Pengembangan Backend");
        project.addDependency("Pengujian dan Debugging", "Pengembangan Frontend");
        project.addDependency("Implementasi Fitur Manajemen Tugas", "Pengujian dan Debugging");
        project.addDependency("Integrasi dengan Alat Kolaborasi", "Implementasi Fitur Manajemen Tugas");

        // Menambahkan proyek baru ke dalam manajer proyek
        pm.addProject(new Project("Proyek Memasak", "apa hayo"));

        // Mendapatkan objek proyek berdasarkan nama
        Project cookingProject = pm.getProjectByName("Proyek Memasak");

        // Menambahkan tugas-tugas ke dalam proyek memasak
        cookingProject.addTask(new Task("Mencari Resep", "Mencari resep masakan yang ingin dimasak", 2, 2, false));
        cookingProject.addTask(new Task("Belanja Bahan", "Membeli semua bahan yang dibutuhkan", 3, 1, false));
        cookingProject.addTask(new Task("Persiapan Bahan", "Membersihkan, memotong, dan mempersiapkan bahan masakan", 4, 2, false));
        cookingProject.addTask(new Task("Memasak", "Memasak makanan sesuai dengan resep yang telah ditemukan", 5, 3, false));
        cookingProject.addTask(new Task("Penyajian", "Menyajikan makanan secara menarik dan rapi", 3, 2, false));

        // Menambahkan dependensi antara tugas-tugas dalam proyek memasak
        cookingProject.addDependency("Belanja Bahan", "Mencari Resep");
        cookingProject.addDependency("Persiapan Bahan", "Belanja Bahan");
        cookingProject.addDependency("Memasak", "Persiapan Bahan");
        cookingProject.addDependency("Penyajian", "Memasak");

        // Menambahkan proyek baru ke dalam manajer proyek
        pm.addProject(new Project("Proyek Kompleks", "siap pak"));

        // Mendapatkan objek proyek berdasarkan nama
        Project complexProject = pm.getProjectByName("Proyek Kompleks");

        // Menambahkan tugas-tugas ke dalam proyek kompleks
        complexProject.addTask(new Task("Tugas A", "Deskripsi Tugas A", 1, 1, false));
        complexProject.addTask(new Task("Tugas B", "Deskripsi Tugas B", 2, 1, false));
        complexProject.addTask(new Task("Tugas C", "Deskripsi Tugas C", 3, 1, false));
        complexProject.addTask(new Task("Tugas D", "Deskripsi Tugas D", 4, 1, false));
        complexProject.addTask(new Task("Tugas E", "Deskripsi Tugas E", 5, 1, false));
        complexProject.addTask(new Task("Tugas F", "Deskripsi Tugas F", 6, 1, false));
        complexProject.addTask(new Task("Tugas G", "Deskripsi Tugas G", 7, 1, false));
        complexProject.addTask(new Task("Tugas H", "Deskripsi Tugas H", 8, 1, false));

        // Menambahkan dependensi antara tugas-tugas dalam proyek kompleks
        complexProject.addDependency("Tugas B", "Tugas A");
        complexProject.addDependency("Tugas C", "Tugas A");
        complexProject.addDependency("Tugas C", "Tugas B");
        complexProject.addDependency("Tugas D", "Tugas B");
        complexProject.addDependency("Tugas D", "Tugas C");
        complexProject.addDependency("Tugas E", "Tugas D");
        complexProject.addDependency("Tugas F", "Tugas C");
        complexProject.addDependency("Tugas G", "Tugas E");
        complexProject.addDependency("Tugas H", "Tugas F");
        pm.addDependency("Proyek Kompleks", "Proyek Memasak");
        pm.addDependency("Proyek Memasak", "Pengembangan Aplikasi Manajemen Proyek Kolaboratif");


        while(true) {
            clearTerminal();
            System.out.println("Welcome to Project Manager!");
            System.out.println("Main Menu: ");
            System.out.println("1. Create project with dependencies");
            System.out.println("2. Create project without dependencies");
            System.out.println("3. Delete project");
            System.out.println("4. Add project dependency");
            System.out.println("5. Delete project dependency");
            System.out.println("6. Select project");
            System.out.println("7. Get list of projects");
            System.out.println("8. Timeline of project with topological sort");
            System.out.println("9. Exit");
            System.out.print("Input your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch(choice) {
                case 1:
                    inputCreateProjectWithDependency(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 2:
                    inputCreateProjectWithoutDependency(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 3:
                    inputDeleteProject(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 4:
                    inputAddDependency(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 5:
                    inputDeleteDependency(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 6:
                    inputSelectProject(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 7:
                    System.out.println(pm.getProjetcsName());
                    waitForAnyKey(sc);
                    break;
                case 8:
                    displayProjectTopologicalSort(pm);
                    waitForAnyKey(sc);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    public static void inputCreateProjectWithDependency(Scanner sc, ProjectManager pm){
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        System.out.print("Input description of project: ");
        String desc = sc.nextLine();
        System.out.print("Input dependency name: ");
        String depName = sc.nextLine();
        pm.addProject(new Project(name, desc));
        pm.addDependency(name, depName);
        System.out.println("Project "+name+" has been created.");
    }
    
    public static void inputCreateProjectWithoutDependency(Scanner sc, ProjectManager pm) {
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        System.out.print("Input description of project: ");
        String desc = sc.nextLine();
        pm.addProject(new Project(name, desc));
        System.out.println("Project "+name+" has been created.");
    }
    
    public static void inputDeleteProject(Scanner sc, ProjectManager pm) {
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        pm.deleteProject(name);
        System.out.println("Project "+name+" has been deleted.");
    }

    public static void inputSelectProject(Scanner sc, ProjectManager pm) {
        System.out.println(pm.getProjetcsName());
        System.out.print("Input no project");
        int id = sc.nextInt()-1;
        Project p = pm.getProjectById(id);
        printProjectMenu(sc, p);
    }

    public static void inputAddDependency(Scanner sc, ProjectManager pm) {
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        System.out.print("Input dependency name: ");
        String depName = sc.nextLine();
        pm.addDependency(name, depName);
    }

    public static void inputDeleteDependency(Scanner sc, ProjectManager pm) {
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        System.out.print("Input dependency name: ");
        String depName = sc.nextLine();
        pm.deleteDependency(name, depName);
    }

    public static void displayProjectTopologicalSort(ProjectManager pm) {
        List<Integer> topologicalSort = pm.topologicalSort();
        int size = topologicalSort.size();

        for(int i = size-1; i>=0; i--) {
            System.out.println((size-i) + " " + pm.getProjectNameById(topologicalSort.get(i)));
            List<Integer> temp = pm.getProjectById(topologicalSort.get(i)).topologicalSort();
            for(int j=temp.size()-1; j>=0; j--){
                System.out.println(" -> "+pm.getProjectById(topologicalSort.get(i)).getTaskNameByID(temp.get(j)));
            }
            System.out.println();
        }        
    }

    public static void printProjectMenu(Scanner sc, Project p) {
        while(true){
            clearTerminal();
            System.out.println("Selected project name: "+p.getProjectName());
            System.out.println("Project Menu: ");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Add dependency for task");
            System.out.println("4. Delete dependency for task");
            System.out.println("5. Get tasks names in project");
            System.out.println("6. Get tasks by priority in project");
            System.out.println("8. Timeline of tasks in project with topological sort");
            System.out.println("9. Select task");
            System.out.println("10. Edit project property");
            System.out.println("11. Display project detail");
            System.out.println("12. Exit project");
            System.out.print("Input your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch(choice) {
                case 1:
                    p.addTask(inputAddTask(sc));
                    waitForAnyKey(sc);
                    break;
                case 2:
                    inputDeleteTask(sc, p);
                    waitForAnyKey(sc);
                    break;
                case 3:
                    inputAddDependency(sc, p);
                    waitForAnyKey(sc);
                    break;
                case 4:
                    inputDeleteDependency(sc, p);
                    waitForAnyKey(sc);
                    break;
                case 5:
                    System.out.println(p.getTasksName());
                    waitForAnyKey(sc);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    displayTaskTopologicalSort(p);
                    waitForAnyKey(sc);
                    break;
                case 9:
                    editTask(sc, p);
                    break;
                case 10:
                    editProject(sc, p);
                    break;
                case 11:
                    System.out.println(p.displayProjectDetail());
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    public static Task inputAddTask(Scanner sc) {
        System.out.print("Input task name: ");
        String name = sc.nextLine();
        System.out.print("Input description of task: ");
        String desc = sc.nextLine();
        System.out.print("Input weight of task: ");
        int weight = sc.nextInt();
        sc.nextLine();
        System.out.print("Input priority of task: (1-5, default=3)");
        int priority = sc.nextInt();
        sc.nextLine();
        return new Task(name, desc, weight, priority, false);
    }

    public static void inputDeleteTask(Scanner sc, Project p) {
        System.out.print("Input task name: ");
        String name = sc.nextLine();
        p.deleteTask(name);
    }

    public static void inputAddDependency(Scanner sc, Project p) {
        System.out.print("Input task name: ");
        String name = sc.nextLine();
        System.out.print("Input dependency name: ");
        String depName = sc.nextLine();
        p.addDependency(name, depName);
    }

    public static void inputDeleteDependency(Scanner sc, Project p) {
        System.out.print("Input task name: ");
        String name = sc.nextLine();
        System.out.print("Input dependency name: ");
        String depName = sc.nextLine();
        p.deleteDependency(name, depName);
    }

    public static void displayTaskTopologicalSort(Project p) {
        List<Integer> topologicalSort = p.topologicalSort();
        for (int i = topologicalSort.size()-1; i>=0; i--) {
            System.out.print(p.getTaskNameByID(topologicalSort.get(i)) + " -> ");
        }
    }

    public static void editProject(Scanner sc, Project p){
        p.displayProjectDetail();
        System.out.println("Edit Project Menu: ");
        System.out.println("1. Edit project name");
        System.out.println("2. Edit project description");
        System.out.println("3. Exit edit project");
        while(true){
            System.out.print("Input your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Input new project name: ");
                    String name = sc.nextLine();
                    p.setProjectName(name);
                    break;
                case 2:
                    System.out.print("Input new project description: ");
                    String desc = sc.nextLine();
                    p.setProjectDescription(desc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }


    public static void editTask(Scanner sc, Project p) {
        clearTerminal();
        System.out.println(p.getTasksName());
        System.out.print("Select task by name: ");
        String choice = sc.nextLine();
        Task t = p.getTaskByName(choice);

        while(true){
            System.out.println("\n\n\n");
            System.out.println("Task Menu: ");
            System.out.println("1. Edit task name");
            System.out.println("2. Edit task description");
            System.out.println("3. Edit task weight");
            System.out.println("4. Edit task priority");
            System.out.println("5. Edit task status");
            System.out.println("to edit task dependency, go to project menu");
            System.out.println("6. Display task detail");
            System.out.println("7. Exit task");
            System.out.print("Input your choice: ");

            int choice2 = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(choice2) {
                case 1:
                    System.out.print("Input new task name: ");
                    String name = sc.nextLine();
                    t.setTaskName(name);
                    break;
                case 2:
                    System.out.print("Input new task description: ");
                    String desc = sc.nextLine();
                    t.setTaskDescription(desc);
                    break;
                case 3:
                    System.out.print("Input new task weight: ");
                    int weight = sc.nextInt();
                    sc.nextLine();
                    t.setTaskWeight(weight);
                    break;
                case 4:
                    System.out.print("Input new task priority: ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    t.setTaskPriority(priority);
                    break;
                case 5:
                    System.out.print("Input new task status: (complete=true, incomplete=false)");
                    boolean status = sc.nextBoolean();
                    t.setTaskStatus(status);
                    break;
                case 6:
                    t.printDisplay();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void clearTerminal() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void waitForAnyKey(Scanner sc) {
        System.out.println("\nTekan tombol apapun untuk melanjutkan...");
        
        try {
            sc.nextLine(); // Menunggu sampai pengguna memencet tombol apapun
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        printMainMenu(new Scanner(System.in));
    }
}
