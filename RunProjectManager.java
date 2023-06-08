// Struktur data: graf
// Sifat graf: acyclic, weighted, directed
// Representasi graf: adjacency list
// Algoritma: dfs untuk melakukan topological sort

// Fitur utama:
// Add project & add task = add vertex
// Add dependency = add edge
// Delete project & delete task = delete vertex
// Delete dependency = delete edge
// Select project = select vertex
// Get list of projects = get list of vertices

// Fitur tambahan:
// Edit project and task property = edit vertex property
// Display project and task detail

import java.util.*;

public class RunProjectManager {

    public static void printMainMenu(Scanner sc, ProjectManager pm) {
        
        while (true) {
            waitForAnyKey(sc);
            clearTerminal();
            System.out.println("Welcome to Project Manager!");
            System.out.println("Main Menu: ");
            System.out.println("1. Create project with dependencies");
            System.out.println("2. Create project without dependencies");
            System.out.println("3. Add project dependency");
            System.out.println("4. Delete project");
            System.out.println("5. Delete project dependency");
            System.out.println("6. Select project");
            System.out.println("7. Get list of projects");
            System.out.println("8. The work order of the projects");
            System.out.println("9. Summary of projects");
            System.out.println("10. Exit");
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
                    inputAddDependency(sc, pm);
                    waitForAnyKey(sc);
                    break;
                case 4:
                    inputDeleteProject(sc, pm);
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
                    System.out.println(pm.getListProjects());
                    waitForAnyKey(sc);
                    break;
                case 8:
                    displayProjectsOrder(pm);
                    waitForAnyKey(sc);
                    break;
                case 9:
                    displaySummary(pm);
                    waitForAnyKey(sc);
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input.");
                    waitForAnyKey(sc);
            }
        }
    }

    public static void inputCreateProjectWithDependency(Scanner sc, ProjectManager pm) {
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        System.out.print("Input description of project: ");
        String desc = sc.nextLine();
        pm.addProject(new Project(name, desc));
        System.out.print("Input dependency name: ");
        String depName = sc.nextLine();
        pm.addDependency(name, depName);
        System.out.println("Project " + name + " has been created.");
    }

    public static void inputCreateProjectWithoutDependency(Scanner sc, ProjectManager pm) {
        System.out.print("Input project name: ");
        String name = sc.nextLine();
        System.out.print("Input description of project: ");
        String desc = sc.nextLine();
        pm.addProject(new Project(name, desc));
        System.out.println("Project " + name + " has been created.");
    }

    public static void inputDeleteProject(Scanner sc, ProjectManager pm) {
        System.out.println(pm.getListProjects());
        System.out.print("Input no project: ");
        int id = sc.nextInt() - 1;
        String name = pm.getProjectNameById(id);
        pm.deleteProject(id);
        System.out.println("Project " + name + " has been deleted.");
    }

    public static void inputSelectProject(Scanner sc, ProjectManager pm) {
        System.out.println(pm.getListProjects());
        System.out.print("Input no project: ");
        int id = sc.nextInt() - 1;
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

    public static void displayProjectsOrder(ProjectManager pm) {
        List<Integer> topologicalSort = pm.topologicalSort();
        int size = topologicalSort.size();
        System.out.println("The work order of the projects: ");

        for (int i = size - 1; i >= 0; i--) {

            String s = pm.getProjectNameById(topologicalSort.get(i));
            Project p = pm.getProjectById(topologicalSort.get(i));

            if (p.getProjectStatus())
                System.out.println((size - i) + " " + s + " (completed)");
            else
                System.out.println((size - i) + " " + s);

            List<Integer> temp = p.topologicalSort();

            for (int j = temp.size() - 1; j >= 0; j--) {

                s = p.getTaskNameByID(temp.get(j));
                Task t = p.getTaskByID(temp.get(j));

                if (t.getTaskStatus())
                    System.out.println((size - i) + "." + (temp.size() - j) + " " + s + " (completed)");
                else
                    System.out.println((size - i) + "." + (temp.size() - j) + " " + s);
            }
            System.out.println();
        }
    }

    public static void displaySummary(ProjectManager pm) {}
    // System.out.println("Total projects: " + pm.nProjects);
    // Set<List<Project>> projects = pm.projects.values();
    // // for(Project p : pm.projects) {
    // // System.out.println("Project name: " + p.getProjectName());
    // // System.out.println("Total tasks: " + p.nTasks);
    // // System.out.println("Total weight: " + p.totalProjectWeight);
    // // System.out.println("Total completed tasks: " + p.getCompletedTasks());
    // // System.out.println("Total uncompleted tasks: " + p.getUncompletedTasks());
    // // System.out.println("Total completed weight: " + p.getCompletedWeight());
    // // System.out.println("Total uncompleted weight: " +
    // p.getUncompletedWeight());
    // // System.out.println("Total completed percentage: " +
    // p.getCompletedPercentage());
    // // System.out.println("Total uncompleted percentage: " +
    // p.getUncompletedPercentage());
    // // System.out.println();
    // // }
    // System.out.println("Total tasks: " + pm.getTotalTasks());
    // }

    public static void printProjectMenu(Scanner sc, Project p) {
        while (true) {
            clearTerminal();
            System.out.println("Current project name: " + p.getProjectName());
            System.out.println(p.getProjectDescription());
            System.out.println(p.projectProgress());
            System.out.println("\nProject Menu: ");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Add dependency for task");
            System.out.println("4. Delete dependency for task");
            System.out.println("5. Get list of tasks in project");
            System.out.println("6. Get tasks by priority in project");
            System.out.println("7. Sort tasks in project by weight");
            System.out.println("8. The work order of the tasks");
            System.out.println("9. Select task");
            System.out.println("10. Edit project property");
            System.out.println("11. Display project detail");
            System.out.println("12. Exit project");
            System.out.print("Input your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    inputAddTask(sc, p);
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
                    inputGetTaskByPriority(sc, p);
                    waitForAnyKey(sc);
                    break;
                case 7:
                    displaySortTaskByWeight(p);
                    waitForAnyKey(sc);
                    break;
                case 8:
                    displayTaskOrder(p);
                    waitForAnyKey(sc);
                    break;
                case 9:
                    selectTask(sc, p);
                    break;
                case 10:
                    editProject(sc, p);
                    break;
                case 11:
                    System.out.println(p.displayProjectDetail());
                    waitForAnyKey(sc);
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    public static void inputAddTask(Scanner sc, Project p) {
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
        p.addTask(new Task(name, desc, weight, priority, false));
    }

    public static void inputDeleteTask(Scanner sc, Project p) {
        System.out.println(p.getTasksName());
        System.out.print("Input no task: ");
        int id = sc.nextInt();
        sc.nextLine();
        p.deleteTask(id);
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

    public static void inputGetTaskByPriority(Scanner sc, Project p) {
        System.out.print("Input priority scale (1-5): ");
        int priority = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for (List<Task> t : p.getTaskByPriority(priority)) {
            sb.append("-> " + t.get(0).getTaskName() + "\n");
        }
        if (sb.toString() != "")
            System.out.println(sb.toString());
        else
            System.out.println("No task with priority scale " + priority + " found.");
    }

    public static void displaySortTaskByWeight(Project p) {
        for (Task t : p.sortTaskByWeight()) {
            System.out.println("-> " + t.getTaskName() + ",\t" + t.getTaskWeight() + "\n");
        }
    }

    public static void displayTaskOrder(Project p) {
        List<Integer> topologicalSort = p.topologicalSort();
        for (int i = topologicalSort.size() - 1; i >= 0; i--) {
            String temp = p.getTaskNameByID(topologicalSort.get(i));
            Task t = p.getTaskByID(topologicalSort.get(i));
            if (t.getTaskStatus())
                System.out.println("-> " + temp + " (complete)");
            else
                System.out.println("-> " + temp);
        }
    }

    public static void editProject(Scanner sc, Project p) {
        while (true) {
            clearTerminal();
            System.out.println(p.displayProjectDetail());
            System.out.println("Edit Project Menu: ");
            System.out.println("1. Edit project name");
            System.out.println("2. Edit project description");
            System.out.println("3. Exit edit project");
            System.out.print("Input your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
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

    public static void selectTask(Scanner sc, Project p) {
        clearTerminal();
        System.out.println(p.getTasksName());
        System.out.print("Select no task: ");
        int id = sc.nextInt() - 1;
        Task t = p.getTaskByID(id);

        while (true) {
            clearTerminal();
            System.out.println("Task Menu: ");
            System.out.println("1. Edit task name");
            System.out.println("2. Edit task description");
            System.out.println("3. Edit task weight");
            System.out.println("4. Edit task priority");
            System.out.println("5. Edit task status");
            System.out.println("6. Display task detail");
            System.out.println("7. Exit task");
            System.out.println("*to edit task dependency, go to project menu");
            System.out.print("Input your choice: ");

            int choice2 = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice2) {
                case 1:
                    System.out.print("Input new task name: ");
                    String name = sc.nextLine();
                    t.setTaskName(name);
                    waitForAnyKey(sc);
                    break;
                case 2:
                    System.out.print("Input new task description: ");
                    String desc = sc.nextLine();
                    t.setTaskDescription(desc);
                    waitForAnyKey(sc);
                    break;
                case 3:
                    System.out.print("Input new task weight: ");
                    int weight = sc.nextInt();
                    sc.nextLine();
                    t.setTaskWeight(weight);
                    waitForAnyKey(sc);
                    break;
                case 4:
                    System.out.print("Input new task priority: (1: less important, 5: most important)");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    t.setTaskPriority(priority);
                    waitForAnyKey(sc);
                    break;
                case 5:
                    System.out.print("Input new task status: (complete=true, incomplete=false)");
                    boolean status = sc.nextBoolean();
                    t.setTaskStatus(status);
                    waitForAnyKey(sc);
                    break;
                case 6:
                    displayTaskDetail(p, t);
                    waitForAnyKey(sc);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    public static void displayTaskDetail(Project p, Task t){
        // System.out.println(t.displayTaskDetail());
        // System.out.println("Task dependency: ");
        // ArrayList<Task> temp = p.getTaskDependencies(t);
        // for(Task ts : temp){
        //     System.out.println("-> " + ts.getTaskName());
        // }
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
        ProjectManager pm = new ProjectManager();
        // Menambahkan proyek ke dalam manajer proyek
        pm.addProject(new Project("Pengembangan Aplikasi Manajemen Proyek Kolaboratif", "helo"));

        // Mendapatkan objek proyek berdasarkan nama
        Project project = pm.getProjectByName("Pengembangan Aplikasi Manajemen Proyek Kolaboratif");

        // Menambahkan tugas-tugas ke dalam proyek
        project.addTask(new Task("Analisis Kebutuhan Pengguna",
                "Melakukan analisis kebutuhan pengguna untuk mengidentifikasi fitur-fitur yang diperlukan dalam aplikasi",
                1, 1, false));
        project.addTask(new Task("Desain Antarmuka Pengguna (UI/UX)",
                "Merancang antarmuka pengguna yang intuitif dan menarik untuk aplikasi", 1, 1, false));
        project.addTask(new Task("Pengembangan Backend",
                "Mengembangkan logika bisnis dan API backend untuk mengelola data proyek dan tugas", 1, 1, false));
        project.addTask(new Task("Pengembangan Frontend",
                "Membangun antarmuka pengguna interaktif menggunakan HTML, CSS, dan JavaScript", 1, 1, false));
        project.addTask(new Task("Pengujian dan Debugging",
                "Melakukan pengujian perangkat lunak, menemukan dan memperbaiki bug, serta memastikan aplikasi berjalan dengan baik",
                1, 1, false));
        project.addTask(new Task("Implementasi Fitur Manajemen Tugas",
                "Mengimplementasikan fitur-fitur manajemen tugas seperti pembuatan, penugasan, pemantauan, dan penyelesaian tugas",
                1, 1, false));
        project.addTask(new Task("Integrasi dengan Alat Kolaborasi",
                "Mengintegrasikan aplikasi dengan alat kolaborasi eksternal seperti Slack atau Microsoft Teams", 1, 1,
                false));

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
        cookingProject.addTask(new Task("Mencari Resep", "Mencari resep masakan yang ingin dimasak", 2, 2, true));
        cookingProject.addTask(new Task("Belanja Bahan", "Membeli semua bahan yang dibutuhkan", 3, 1, true));
        cookingProject.addTask(
                new Task("Persiapan Bahan", "Membersihkan, memotong, dan mempersiapkan bahan masakan", 4, 2, true));
        cookingProject
                .addTask(new Task("Memasak", "Memasak makanan sesuai dengan resep yang telah ditemukan", 5, 3, true));
        cookingProject.addTask(new Task("Penyajian", "Menyajikan makanan secara menarik dan rapi", 3, 2, true));

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
        complexProject.addTask(new Task("Tugas A", "Deskripsi Tugas A", 1, 1, true));
        complexProject.addTask(new Task("Tugas B", "Deskripsi Tugas B", 2, 1, false));
        complexProject.addTask(new Task("Tugas C", "Deskripsi Tugas C", 3, 1, true));
        complexProject.addTask(new Task("Tugas D", "Deskripsi Tugas D", 4, 1, false));
        complexProject.addTask(new Task("Tugas E", "Deskripsi Tugas E", 5, 1, true));
        complexProject.addTask(new Task("Tugas F", "Deskripsi Tugas F", 6, 1, false));
        complexProject.addTask(new Task("Tugas G", "Deskripsi Tugas G", 7, 1, true));
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
        pm.addDependency("Pengembangan Aplikasi Manajemen Proyek Kolaboratif", "Proyek Kompleks");
        // Menambahkan proyek baru ke dalam manajer proyek
        pm.addProject(new Project("Proyek Teknologi", "halo semua"));

        // Mendapatkan objek proyek berdasarkan nama
        Project techProject = pm.getProjectByName("Proyek Teknologi");

        // Menambahkan tugas-tugas ke dalam proyek teknologi
        techProject.addTask(
                new Task("Analisis Kebutuhan", "Menganalisis kebutuhan pengguna untuk sistem baru", 5, 2, false));
        techProject.addTask(new Task("Rancangan Sistem", "Merancang arsitektur dan antarmuka sistem", 8, 3, false));
        techProject.addTask(new Task("Pengembangan Modul A", "Mengembangkan modul A sistem", 10, 4, false));
        techProject.addTask(new Task("Pengembangan Modul B", "Mengembangkan modul B sistem", 9, 4, false));
        techProject
                .addTask(new Task("Pengujian dan Debugging", "Melakukan pengujian dan debugging sistem", 6, 2, false));
        techProject.addTask(new Task("Integrasi Modul", "Mengintegrasikan modul-modul sistem", 7, 3, false));
        techProject.addTask(new Task("Pengujian Sistem", "Melakukan pengujian keseluruhan sistem", 8, 3, false));
        techProject.addTask(new Task("Peluncuran Sistem", "Melakukan peluncuran sistem", 5, 2, false));
        techProject.addTask(
                new Task("Pemeliharaan dan Pembaruan", "Melakukan pemeliharaan dan pembaruan sistem", 4, 1, false));

        // Menambahkan dependensi antara tugas-tugas dalam proyek teknologi
        techProject.addDependency("Rancangan Sistem", "Analisis Kebutuhan");
        techProject.addDependency("Pengembangan Modul A", "Rancangan Sistem");
        techProject.addDependency("Pengembangan Modul B", "Rancangan Sistem");
        techProject.addDependency("Pengujian dan Debugging", "Pengembangan Modul A");
        techProject.addDependency("Pengujian dan Debugging", "Pengembangan Modul B");
        techProject.addDependency("Integrasi Modul", "Pengujian dan Debugging");
        techProject.addDependency("Pengujian Sistem", "Integrasi Modul");
        techProject.addDependency("Peluncuran Sistem", "Pengujian Sistem");
        techProject.addDependency("Pemeliharaan dan Pembaruan", "Peluncuran Sistem");

        // Menambahkan proyek baru ke dalam manajer proyek
        pm.addProject(new Project("Proyek Bisnis", "semoga berhasil"));

        // Mendapatkan objek proyek berdasarkan nama
        Project bisnisProject = pm.getProjectByName("Proyek Bisnis");

        // Menambahkan tugas-tugas ke dalam proyek bisnis
        bisnisProject.addTask(new Task("Analisis Pasar", "Menganalisis pasar untuk produk baru", 8, 3, false));
        bisnisProject.addTask(
                new Task("Riset dan Pengembangan", "Melakukan riset dan pengembangan produk baru", 12, 4, false));
        bisnisProject.addTask(new Task("Pembuatan Prototipe", "Membuat prototipe produk baru", 10, 3, false));
        bisnisProject.addTask(
                new Task("Pengujian dan Evaluasi", "Mengujikan dan mengevaluasi kualitas produk baru", 6, 2, false));
        bisnisProject.addTask(
                new Task("Pemasaran", "Merencanakan dan melaksanakan strategi pemasaran produk baru", 10, 3, false));
        bisnisProject.addTask(new Task("Distribusi", "Menyiapkan dan mengatur distribusi produk baru", 8, 3, false));
        bisnisProject.addTask(new Task("Peluncuran", "Melakukan peluncuran resmi produk baru", 5, 2, false));

        // Menambahkan dependensi antara tugas-tugas dalam proyek bisnis
        bisnisProject.addDependency("Riset dan Pengembangan", "Analisis Pasar");
        bisnisProject.addDependency("Pembuatan Prototipe", "Riset dan Pengembangan");
        bisnisProject.addDependency("Pengujian dan Evaluasi", "Pembuatan Prototipe");
        bisnisProject.addDependency("Pemasaran", "Riset dan Pengembangan");
        bisnisProject.addDependency("Pemasaran", "Pengujian dan Evaluasi");
        bisnisProject.addDependency("Distribusi", "Pemasaran");
        bisnisProject.addDependency("Peluncuran", "Distribusi");

        printMainMenu(new Scanner(System.in), pm);
    }
}
