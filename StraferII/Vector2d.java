
public class Vector2d {

    // Members
    public float x;
    public float y;

    // Constructors
    public Vector2d() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Compare two vectors
    public boolean equals(Vector2d other) {
        return (this.x == other.x && this.y == other.y);
    }

    public static double distance(Vector2d a, Vector2d b) {
        float v0 = b.x - a.x;
        float v1 = b.y - a.y;
        return Math.sqrt(v0 * v0 + v1 * v1);
    }

    public float distance(float x, float y) {
        //Finds the x and y distance between both vectors by subtracting their x and y components. 
        //Note which vector is subtracted by which other vector is irrelevent,
        //since the magnitude of the resulting vector will be the same.
        float distX = this.x - x;
        float distY = this.y - y;

        //Returns the magnitude of the distance between both vectors. This is done via the pythagorean theorem, using sqrt(x*x + y*y).
        return (float) Math.sqrt(distX * distX + distY * distY);
    }

    public void normalize() {
        // sets length to 1
        //
        double length = Math.sqrt(x * x + y * y);

        if (length != 0.0) {
            float s = 1.0f / (float) length;
            x = x * s;
            y = y * s;
        }
    }

}

//  https://noobtuts.com/java/vector2-class
