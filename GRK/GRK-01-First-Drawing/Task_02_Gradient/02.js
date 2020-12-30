const CanvasWidth = 1200;
const CanvasHeight = 720;

function setup() {
    createCanvas(CanvasWidth, CanvasHeight);
    noLoop();
}

function draw() {
    //noprotect
    background(0);
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            let myGradientColor = x / width * 256;
            set(x, y, color(myGradientColor));
        }
    }
    updatePixels();
};