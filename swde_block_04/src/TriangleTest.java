import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    void testNewTriangle_shouldWork() {
        Triangle t = Triangle.createInstance(10, 15, 20);
        assertNotNull(t);
    }
    @Test
    void testNewTriangle_shouldThrowException_TriangleNotPossible() {
        assertThrows(IllegalArgumentException.class, () -> Triangle.createInstance(10, 10, 50));
    }
    @Test
    void testNewTriangle_shouldThrowException_ValueForTriangleSideNegative() {
        assertThrows(IllegalArgumentException.class, () -> Triangle.createInstance(10, -15, 20));
    }
    @Test
    void testCalculateArea_shouldWork() {
        Triangle t = Triangle.createInstance(3, 4, 5);
        double area = t.calculateArea();
        assertEquals(6, area);
    }
}
