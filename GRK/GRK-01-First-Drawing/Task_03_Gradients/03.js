const CanvasWidth = 1200;
const CanvasHeight = 720;

function setup() {
    createCanvas(CanvasWidth, CanvasHeight);
    noLoop();
}

function draw() {
    //noprotect
    background(0);
    let HalfDiagonal = sqrt(CanvasWidth * CanvasWidth + CanvasHeight * CanvasHeight) / 2;
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            let xDistance = x - (width / 2);
            let yDistance = y - (height / 2);
            let Distance = sqrt(xDistance * xDistance + yDistance * yDistance);
            let myRedGradient = (1 - Distance / HalfDiagonal) * 256;
            let myGreenGradient = (Distance / (HalfDiagonal)) * 256;
            let myBlueGradient = ((x + y) / (height + width)) * 256;
            set(x, y, color(myRedGradient, myGreenGradient, myBlueGradient));
        }
    }
    updatePixels();
};