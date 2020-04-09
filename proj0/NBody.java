/** The universe */
public class NBody {

    public static final String imageFolder = "images/";
    public static final String backGroundName = "starfield.jpg";
    public static final int waitTimeMilliseconds = 10;
    
    /** Read radius from file */
    public static double readRadius(String file){
        In in = new In(file);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** read universe(planets) info from file */
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int numOfPlanet = in.readInt();
        Planet[] planets = new Planet[numOfPlanet]; // We can use variable to intialize an array(C can't)

        in.readDouble();
        int i = 0;
        while(i < numOfPlanet){ // instead of "while(!in.isEmpty)", because in "planets.txt" file, there are something that is not data.
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, image);
            i++;
        }
        return planets;
    }

    public static void main(String[] args){
        /* Collecting All Needed Input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        /* Drawing the Background */
        StdDraw.setScale(-radius, radius); // Must setup first
        StdDraw.clear();
        StdDraw.picture(0, 0, imageFolder + backGroundName, 2 * radius, 2 * radius);
        /* Drawing All of the Planets */
        for(Planet p : planets){
            p.draw();
        }
        StdDraw.show();
        /** God said, "Let there be light", and there was light. */
        StdDraw.enableDoubleBuffering();
        double tNow = 0;
        while(tNow < T){
            Double[] xForces = new Double[planets.length]; // for each loop "create" an array is ok?
            Double[] yForces = new Double[planets.length];
            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, imageFolder + backGroundName, 2 * radius, 2 * radius);
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(waitTimeMilliseconds);
            tNow++;
        }
        /** At the end of the universe, everything is recorded */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}