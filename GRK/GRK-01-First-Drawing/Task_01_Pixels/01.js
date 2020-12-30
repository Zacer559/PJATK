function setup() {
    createCanvas(1200, 720);
    noLoop();
    frameRate(25);
}

function draw() {
    //noprotect
    background(0);
    //changing color of all pixels to magenta
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            set(x, y, color(255, 0, 255));
        }
    }
    updatePixels();
};