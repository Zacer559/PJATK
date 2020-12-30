const CanvasWidth = 800;
const CanvasHeight = 600;
const Ax = 400, Ay = 100;
const Bx = 100, By = 500;
const Cx = 700, Cy = 500;

function setup() {
    createCanvas(CanvasWidth, CanvasHeight);
    noLoop();
}

function draw() {
    background(0);
    stroke(color(255, 255, 255)); // Change the color
    point(Ax, Ay);
    point(Bx, By);
    point(Cx, Cy);
    let Gx = Ax;
    let Gy = Ay;
    for (let z = 0; z < 10000; z++) {
        let randomized = floor(random(0, 3));
        switch (randomized) {
            case 0 :
                Gx=(Gx+Ax)/2;
                Gy=(Gy+Ay)/2;
                point(Gx,Gy);
                break;
            case 1:
                Gx=(Gx+Bx)/2;
                Gy=(Gy+By)/2;
                point(Gx,Gy);
                break;
            default :
                Gx=(Gx+Cx)/2;
                Gy=(Gy+Cy)/2;
                point(Gx,Gy);
                break;
        }
    }
    updatePixels();
}