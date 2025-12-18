import java.util.Arrays;

/**
 * Represents the grades of a CSC 216 student.
 * Grades for CSC 217 are an average of the labs.  
 * CSC 216 grades are more interesting to calculate.
 * @author Dr. Sarah Heckman
 */
public class Grades {
	
	/** GP1 grade */
	private double gp1;
	/** GP2 grade */
	private double gp2;
	/** GP3 grade */
	private double gp3;
	/** P1P1 grade */
	private double p1p1;
	/** P1P2 grade */
	private double p1p2;
	/** P2P1 grade */
	private double p2p1;
	/** P2P2 grade */
	private double p2p2;
	/** Exam 1 grade */
	private double exam1;
	/** Exam 2 grade */
	private double exam2;
	/** Exam 3 grade */
	private double exam3;
	
	/** Percent of Guided Projects in final grade */
	private static final int GP_PERCENT = 20;
	/** Percent of Guided Project 1 in final grade */
	private static final int GP1_PERCENT = 4;
	/** Percent of Guided Project 2 in final grade */
    private static final int GP2_PERCENT = 6;
    /** Percent of Guided Project 3 in final grade */
    private static final int GP3_PERCENT = 10;
	/** Percent of Part 1 in project grade */
	private static final int PART1_PERCENT = 20;
	/** Percent of Part 2 in project grade */
	private static final int PART2_PERCENT = 80;
	/** Percent of Project average in final grade */
	private static final int PROJECTS_PERCENT = 42;
	/** Percent of exams in final grade */
	private static final int EXAM_PERCENT = 38;
	/** Percent of Exam 1 in final grade */
	private static final int EXAM1_PERCENT = 9;
	/** Percent of Exam 2 in final grade */
    private static final int EXAM2_PERCENT = 9;
    /** Percent of Exam 13 in final grade */
    private static final int EXAM3_PERCENT = 20;
	/** Threshold for first level of MGR */
	private static final int FIRST_MGR_THRESHOLD = 60;
	/** Threshold for second level of MGR */
	private static final int SECOND_MGR_THRESHOLD = 65;
	/** Highest possible grade for MGR if earning a letter grade */
	private static final int HIGHEST_MGR_GRADE = 72;
	/** Grade cutoff for D-*/
	private static final int D_MINUS = 60;
	/** Grade cutoff for D*/
	private static final int D = 63;
	/** Grade cutoff for D+*/
	private static final int D_PLUS = 67;
	/** Grade cutoff for C-*/
	private static final int C_MINUS = 70;
	/** Grade cutoff for C-*/
	private static final int C = 73;
	/** Grade cutoff for C+*/
	private static final int C_PLUS = 77;
	/** Grade cutoff for B-*/
	private static final int B_MINUS = 80;
	/** Grade cutoff for B*/
	private static final int B = 83;
	/** Grade cutoff for B+*/
	private static final int B_PLUS = 87;
	/** Grade cutoff for A-*/
	private static final int A_MINUS = 90;
	/** Grade cutoff for A*/
	private static final int A = 93;
	/** Grade cutoff for A+*/
	private static final int A_PLUS = 97;
	/** Full score in CSC 216*/
	private static final int FULL_SCORE = 100;
	

	/**
	 * Returns the final numerical grade for the course.
	 */
	public double getFinalGrade() {
		double grade = ((getGuidedProjectsGrade() * GP_PERCENT) + 
		        (getProjectsAverage() * PROJECTS_PERCENT) + 
		        (getExamsGrade()) * EXAM_PERCENT) / FULL_SCORE;
				
		return grade;
	}
	
	/**
	 * Returns the guided projects grade.
	 * @return guided projects grade
	 */
	public double getGuidedProjectsGrade() {
	    double gp1grade = gp1 * GP1_PERCENT;
	    double gp2grade = gp2 * GP2_PERCENT;
	    double gp3grade = (gp3 / 115) * 100 * GP3_PERCENT;
	    return ((gp1grade + gp2grade + gp3grade) / (GP1_PERCENT + GP2_PERCENT + GP3_PERCENT));
	}
	
	/**
     * Returns the projects average grade.
     * @return projects average grade
     */
    public double getProjectsAverage() {
        double project1 = (((p1p1 / 105) * 100 * PART1_PERCENT) + ((p1p2 / 150) * 100 * PART2_PERCENT)) / 100;
        double project2 = (((p2p1 / 105) * 100 * PART1_PERCENT) + ((p2p2 / 150) * 100 * PART2_PERCENT)) / 100;
        return (project1 + project2) / 2;
    }
    
    /**
     * Returns the exam grades.
     * @return exam grades
     */
    public double getExamsGrade() {
        double exam1grade = exam1 * EXAM1_PERCENT;
        double exam2grade = exam2 * EXAM2_PERCENT;
        double exam3grade = exam3 * EXAM3_PERCENT;
        
        return ((exam1grade + exam2grade + exam3grade) / (EXAM1_PERCENT + EXAM2_PERCENT + EXAM3_PERCENT));
    }
	
	/**
	 * Returns the letter grade for CSC216 by considering the minimum grade requirements (MGR)
	 * and grade calculation.
	 * @return letter grade for CSC216
	 */
	public String getFinalLetterGrade() {
		double grade = getFinalGrade();
		double projectMGR = getProjectMGR();
		double projectGPMGR = getProjectGPMGR();
		double examMGR = getExamMGR();
		
		if (projectMGR < FIRST_MGR_THRESHOLD || examMGR < FIRST_MGR_THRESHOLD) {
			return "F";
		}
		
		if (projectGPMGR < SECOND_MGR_THRESHOLD || examMGR < SECOND_MGR_THRESHOLD) {
			grade =  Math.min(HIGHEST_MGR_GRADE, grade);
		}
		
		if (grade >= A_PLUS) return "A+";
		if (grade >= A) return "A";
		if (grade >= A_MINUS) return "A-";
		if (grade >= B_PLUS) return "B+";
		if (grade >= B) return "B+";
		if (grade >= B_MINUS) return "B-";
		if (grade >= C_PLUS) return "C+";
		if (grade >= C) return "C";
		if (grade >= C_MINUS) return "C-";
		if (grade >= D_PLUS) return "D+";
		if (grade >= D) return "D";
		if (grade >= D_MINUS) return "D-";
		if (grade >= 0) return "F";
		return null;
		
	}
	
	/**
	 * Returns the project MGR
	 * @return the project MGR
	 */
	public double getProjectMGR() {
		return getProjectsAverage();
	}
	
	/**
	 * Returns the project and guided project MGR
	 * @return the project and GP MGR
	 */
	public double getProjectGPMGR() {
		double projectGPMGR = 
				((getGuidedProjectsGrade() * GP_PERCENT) +
				(getProjectsAverage() * PROJECTS_PERCENT))  / 
				(GP_PERCENT + PROJECTS_PERCENT);
		return projectGPMGR;
	}
	
	/**
	 * Returns the quiz MGR 
	 * @return quiz MGR
	 */
	public double getExamMGR() {
		return getExamsGrade();
	}

	/**
	 * Returns GP1 grade
	 * @return GP1 grade
	 */
	public double getGp1() {
		return gp1;
	}

	/**
	 * Sets GP1 grade
	 * @param gp1 grade
	 */
	public void setGp1(double gp1) {
		this.gp1 = gp1;
	}

	/**
	 * Returns GP2 grade
	 * @return GP2 grade
	 */
	public double getGp2() {
		return gp2;
	}

	/**
	 * Sets GP2 grade
	 * @param gp2 grade
	 */
	public void setGp2(double gp2) {
		this.gp2 = gp2;
	}

	/**
	 * Returns GP3 grade
	 * @return GP3 grade
	 */
	public double getGp3() {
		return gp3;
	}

	/**
	 * Sets GP3 grade
	 * @param gp3 grade
	 */
	public void setGp3(double gp3) {
		this.gp3 = gp3;
	}

	/**
	 * Returns P1P1 grade
	 * @return P1P1 grade
	 */
	public double getP1p1() {
		return p1p1;
	}

	/**
	 * Sets P1P1 grade
	 * @param p1p1 grade
	 */
	public void setP1p1(double p1p1) {
		this.p1p1 = p1p1;
	}

	/**
	 * Returns P1P2 grade
	 * @return P1P2 grade
	 */
	public double getP1p2() {
		return p1p2;
	}

	/**
	 * Sets P1P2 grade
	 * @param p1p2 grade
	 */
	public void setP1p2(double p1p2) {
		this.p1p2 = p1p2;
	}

	/**
	 * Returns P2P1 grade
	 * @return P2P1 grade
	 */
	public double getP2p1() {
		return p2p1;
	}
	/**
	 * Sets P2P1 grade
	 * @param p2p1 grade
	 */
	public void setP2p1(double p2p1) {
		this.p2p1 = p2p1;
	}

	/**
	 * Returns P2P2 grade
	 * @return P2P2 grade
	 */
	public double getP2p2() {
		return p2p2;
	}
	/**
	 * Sets P2P2 grade
	 * @param p2p2 grade
	 */
	public void setP2p2(double p2p2) {
		this.p2p2 = p2p2;
	}
	
	/**
     * Returns exam1 grade
     * @return exam1 grade
     */
    public double getExam1() {
        return exam1;
    }
    /**
     * Sets exam1 grade
     * @param exam1 grade
     */
    public void setExam1(double exam1) {
        this.exam1 = exam1;
    }
    
    /**
     * Returns exam2 grade
     * @return exam2 grade
     */
    public double getExam2() {
        return exam2;
    }
    /**
     * Sets exam2 grade
     * @param exam2 grade
     */
    public void setExam2(double exam2) {
        this.exam2 = exam2;
    }
    
    /**
     * Returns exam3 grade
     * @return exam3 grade
     */
    public double getExam3() {
        return exam3;
    }
    /**
     * Sets exam3 grade
     * @param exam3 grade
     */
    public void setExam3(double exam3) {
        this.exam3 = exam3;
    }
	
	/**
	 * Returns grade info as a String.
	 * @return grade info
	 */
	@Override
	public String toString() {
		String gradeInfo = "";
		gradeInfo += "Project MGR: " + getProjectMGR() + "\n";
		gradeInfo += "Project + GP MGR: " + getProjectGPMGR() + "\n";
		gradeInfo += "Exam MGR: " + getExamMGR() + "\n";
		gradeInfo += "Final Grade: " + getFinalGrade() + "\n";
		gradeInfo += "Final Letter Grade: " + getFinalLetterGrade();
		return gradeInfo;
	}

}
