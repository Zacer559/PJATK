const CanvasWidth = 800;
const CanvasHeight = 600;

function setup() {
    createCanvas(CanvasWidth, CanvasHeight);
    noLoop();
}

function draw() {
    //making sky
    background(200, 200, 255);
    //making grass
    noStroke();
    fill(0, 100, 0);
    let grassHeight = floor(CanvasHeight * 0.68);
    makeRect(0, grassHeight, CanvasWidth, CanvasHeight, color(0, 100, 0));
    //making building
    let buildW = floor(CanvasWidth * 0.25);
    makeRect(buildW, grassHeight - buildW, CanvasWidth - buildW, grassHeight, color(100, 50, 30));
    // making flowers
    for (z = 0; z < 1000; z++) {
        let randomColor = color(floor(random(0, 255)), floor(random(0, 255)), floor(random(0, 255)));
        let randomPlaceX = floor(random(0, CanvasWidth));
        let randomPlaceY = floor(random(grassHeight, CanvasHeight));
        set(randomPlaceX, randomPlaceY, randomColor);
    }
    //making roof
    for (let y = 50, w = 0; y < grassHeight - buildW; y++, w += 2) {
        for (i = 0; i < w; i++) {
            set(2 * buildW - i, y, color(255, 100, 100));
        }
        for (i = 0; i < w; i++) {
            set(2 * buildW + i, y, color(255, 100, 100));
        }
    }
    updatePixels();
};

function makeRect(BegX, BegY, EndX, EndY, color) {
    for (y = BegY; y < EndY; y++) {
        for (x = BegX; x < EndX; x++) {
            set(x, y, color);
        }
    }

}