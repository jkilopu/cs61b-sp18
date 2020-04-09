/** class planet */
public class Planet {
    
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static final String imageFolder = "images/";
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos; // Can't use: this = p;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /** calculate the force between two planets */
    public double calcDistance(Planet p) {
        double xxDis = Math.abs(this.xxPos - p.xxPos);
        double yyDis = Math.abs(this.yyPos - p.yyPos);
        double r = Math.sqrt(xxDis * xxDis + yyDis * yyDis);
        return r;
    }

    /** calculate the relative force between two planets */
    public double calcForceExertedBy(Planet p) {
        if (this.equals(p)) {
            return 0;
        }
        double r = this.calcDistance(p);
        double force = Planet.G * this.mass * p.mass / (r * r);
        return force;
    }

    /** calculate the relative force in X direction between two planets */
    public double calcForceExertedByX(Planet p) {
        if (this.equals(p)) {
            return 0;
        }
        double force = this.calcForceExertedBy(p), r = this.calcDistance(p);
        double xxForce = force * (p.xxPos - this.xxPos) / r; // Note: p.xxPos - this.xxPos; Can't be reversed.
        return xxForce;
    }

    /** calculate the relative force in Y direction between two planets */
    public double calcForceExertedByY(Planet p) {
        if (this.equals(p)) {
            return 0;
        }
        double force = this.calcForceExertedBy(p), r = this.calcDistance(p);
        double yyForce = force * (p.yyPos - this.yyPos) / r;
        return yyForce;
    }

    /** sum the forces in X direction */
    public double calcNetForceExertedByX(Planet[] all) {
        double xxForce, xxNetForce = 0;
        for (Planet p : all) {
            xxForce = calcForceExertedByX(p);
            xxNetForce += xxForce;
        }
        return xxNetForce;
    }

    /** sum the forces in Y direction */
    public double calcNetForceExertedByY(Planet[] all) {
        double yyForce, yyNetForce = 0;
        for (Planet p : all) {
            yyForce = calcForceExertedByY(p);
            yyNetForce += yyForce;
        }
        return yyNetForce;
    }

    /** update the planet */
    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass, aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    /** draw one planet */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, Planet.imageFolder + this.imgFileName);
    }
}