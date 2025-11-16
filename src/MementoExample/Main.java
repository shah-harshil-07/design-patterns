package MementoExample;

import java.util.*;

class ResumeEditor {
    private String name;
    private String education;
    private String experience;
    private List<String> skills;

    public void setName(String name) {
        this.name = name;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void printResume() {
        System.out.println("x:----- Resume -----");
        System.out.println("Name: " + this.name);
        System.out.println("Education: " + this.education);
        System.out.println("Experience: " + this.experience);
        System.out.println("Skills: " + this.skills);
        System.out.println("x:------------------");
    }

    public Memento save() {
        return new Memento(this.name, this.education, this.experience, this.skills);
    }

    public void restore(Memento memento) {
        this.name = memento.name;
        this.skills = memento.skills;
        this.education = memento.education;
        this.experience = memento.experience;
    }

    public class Memento {
        private String name;
        private String education;
        private String experience;
        private List<String> skills;

        public Memento(String name, String education, String experience, List<String> skills) {
            this.name = name;
            this.education = education;
            this.experience = experience;
            this.skills = List.copyOf(skills);
        }
    }
}

class ResumeHistory {
    private final Stack<ResumeEditor.Memento> history;

    public ResumeHistory() {
        this.history = new Stack<>();
    }

    public void save(ResumeEditor resume) {
        this.history.add(resume.save());
    }

    public void undo(ResumeEditor resume) {
        resume.restore(this.history.pop());
    }
}

public class Main {
    public static void main(String[] args) {
        ResumeEditor editor = new ResumeEditor();
        ResumeHistory history = new ResumeHistory();

        editor.setName("Alice");
        editor.setEducation("B.Tech CSE");
        editor.setExperience("Fresher");
        editor.setSkills(Arrays.asList("Java", "DSA"));
        history.save(editor);

        editor.setExperience("SDE Intern at TUF+");
        editor.setSkills(Arrays.asList("Java", "DSA", "LLD", "Spring Boot"));
        history.save(editor);

        editor.printResume(); // Shows updated experience
        System.out.println();

        history.undo(editor);
        editor.printResume(); // Shows resume after one undo
        System.out.println();

        history.undo(editor);
        editor.printResume(); // Shows resume after second undo (initial state)
    }
}
