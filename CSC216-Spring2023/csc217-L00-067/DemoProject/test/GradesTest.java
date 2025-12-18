import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** 
 * Tests for the Grades class
 * @author Dr. Sarah Heckman
 */
public class GradesTest {

	/**
	 * Test A student
	 */
	@Test
	public void testAStudent() {
		Grades studentA = new Grades();
		studentA.setGp1(97);
		studentA.setGp2(94);
		studentA.setGp3(110);
		studentA.setP1p1(101);
		studentA.setP1p2(142);
		studentA.setP2p1(103);
		studentA.setP2p2(145);
		studentA.setExam1(95);
		studentA.setExam2(97);
		studentA.setExam3(93);
		
		assertEquals(95.26, studentA.getFinalGrade(), 0.01);
		assertEquals("A", studentA.getFinalLetterGrade());
		assertEquals(95.96, studentA.getProjectMGR(), 0.01);
		assertEquals(95.79, studentA.getProjectGPMGR(), 0.01);
		assertEquals(94.42, studentA.getExamMGR(), 0.01);
	}
	
	/**
	 * Test B student
	 */
	@Test
	public void testBStudent() {
		Grades studentB = new Grades();
		studentB.setGp1(87);
		studentB.setGp2(84);
		studentB.setGp3(97);
		studentB.setP1p1(90);
		studentB.setP1p2(119);
		studentB.setP2p1(87);
		studentB.setP2p2(124);
		studentB.setExam1(85);
        studentB.setExam2(87);
        studentB.setExam3(83);

		assertEquals(83.33, studentB.getFinalGrade(), 0.01);
		assertEquals("B", studentB.getFinalLetterGrade());
		assertEquals(81.65, studentB.getProjectMGR(), 0.01);
		assertEquals(82.66, studentB.getProjectGPMGR(), 0.01);
		assertEquals(84.42, studentB.getExamMGR(), 0.01);
	}
	
	/**
	 * Test C student
	 */
	@Test
	public void testCStudent() {
		Grades studentC = new Grades();
		studentC.setGp1(77);
		studentC.setGp2(74);
		studentC.setGp3(87);
		studentC.setP1p1(75);
		studentC.setP1p2(78);
		studentC.setP2p1(77);
		studentC.setP2p2(74);
		studentC.setExam1(75);
        studentC.setExam2(77);
        studentC.setExam3(73);
	}
	
	/**
     * Test Exam MGR student
     */
    @Test
    public void testQuizMGRStudent() {
        Grades studentMGR = new Grades();
        studentMGR.setGp1(97);
        studentMGR.setGp2(94);
        studentMGR.setGp3(110);
        studentMGR.setP1p1(101);
        studentMGR.setP1p2(142);
        studentMGR.setP2p1(103);
        studentMGR.setP2p2(145);
        studentMGR.setExam1(65);
        studentMGR.setExam2(53);
        studentMGR.setExam3(42);
        
        assertEquals(78.40, studentMGR.getFinalGrade(), 0.01);
        assertEquals("F", studentMGR.getFinalLetterGrade());
        assertEquals(95.96, studentMGR.getProjectMGR(), 0.01);
        assertEquals(95.79, studentMGR.getProjectGPMGR(), 0.01);
        assertEquals(50.05, studentMGR.getExamMGR(), 0.01);
    }
    
    /**
     * Test Project+GP MGR student 
     */
    @Test
    public void testProjectGPMGRStudent() {
        Grades studentMGR = new Grades();
        studentMGR.setGp1(0);
        studentMGR.setGp2(14);
        studentMGR.setGp3(50);
        studentMGR.setP1p1(101);
        studentMGR.setP1p2(115);
        studentMGR.setP2p1(103);
        studentMGR.setP2p2(105);
        studentMGR.setExam1(95);
        studentMGR.setExam2(97);
        studentMGR.setExam3(93);
        
        assertEquals(73.86, studentMGR.getFinalGrade(), 0.01);
        assertEquals("C-", studentMGR.getFinalLetterGrade());
        assertEquals(78.09, studentMGR.getProjectMGR(), 0.01);
        assertEquals(61.27, studentMGR.getProjectGPMGR(), 0.01);
        assertEquals(94.42, studentMGR.getExamMGR(), 0.01);
    }
    
    /**
     * Test Project MGR student
     */
    @Test
    public void testProjectMGRStudent() {
        Grades studentMGR = new Grades();
        studentMGR.setGp1(67);
        studentMGR.setGp2(93);
        studentMGR.setGp3(114);
        studentMGR.setP1p1(37);
        studentMGR.setP1p2(65);
        studentMGR.setP2p1(68);
        studentMGR.setP2p2(91);
        studentMGR.setExam1(95);
        studentMGR.setExam2(97);
        studentMGR.setExam3(93);
        
        assertEquals(75.72, studentMGR.getFinalGrade(), 0.01);
        assertEquals("F", studentMGR.getFinalLetterGrade());
        assertEquals(51.60, studentMGR.getProjectMGR(), 0.01);
        assertEquals(64.26, studentMGR.getProjectGPMGR(), 0.01);
        assertEquals(94.42, studentMGR.getExamMGR(), 0.01);
    }
	
}
