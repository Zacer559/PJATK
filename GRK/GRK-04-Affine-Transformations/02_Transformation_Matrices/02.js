var imgA;
var imgB;

function setup() {
    createCanvas(512, 512);
    background(255);
    imgA = createImage(512, 512);
    imgB = createImage(512, 512);
    imgA.loadPixels();
    imgB.loadPixels();
    var d = pixelDensity();
    for (var i = 0; i < 512 * 512 * 4 * d; i += 4) {
        imgA.pixels[i] = 240;
        imgA.pixels[i + 1] = 250;
        imgA.pixels[i + 2] = 240;
        imgA.pixels[i + 3] = 255;
        imgB.pixels[i] = 240;
        imgB.pixels[i + 1] = 240;
        imgB.pixels[i + 2] = 250;
        imgB.pixels[i + 3] = 255;
    }
    imgA.updatePixels();
    imgB.updatePixels();
}

function draw() {
    if (!keyIsDown(32)) {
        image(imgA, 0, 0);
        text('Image A', 10, 20);
    } else {
        image(imgB, 0, 0);
        text('Image B', 10, 20);
    }
}

function makeVector(x, y) {
    return [x, y, 1];
}

function drawVector(img, vec) {
    img.loadPixels();
    img.set(vec[0], vec[1], 0);
    img.updatePixels();
}

function mouseDragged() {
    drawVector(imgA, makeVector(mouseX, mouseY));
}

function makeIdentity() {
    return [[1, 0, 0], [0, 1, 0], [0, 0, 1]];
}

function makeShift(tx, ty) {
    return [[1, 0, tx], [0, 1, ty], [0, 0, 1]];
}

function makeScale(sx, sy) {
    return [[sx, 0, 0], [0, sy, 0], [0, 0, 1]];
}

function makeRotate(fi) {
    return [[Math.cos(fi), -Math.sin(fi), 0], [Math.sin(fi), -Math.cos(fi), 0], [0, 0, 1]];
}

function makeShear(shx, shy) {
    return [[1, shx, 0], [shy, 1, 0], [0, 0, 1]];
}

console.log(makeVector(2, 3));
console.log(makeIdentity());
console.log(makeShift(2, 3));
console.log(makeScale(2, 3));
console.log(makeRotate(90 / 180 * Math.PI));
console.log(makeShear(2, 3));