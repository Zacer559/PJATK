function setup() {
    createCanvas(512, 512);
    background(255);
}

var x0 = -1;
var y0 = -1;
var x1 = -1;
var y1 = -1;

function mousePressed() {
    x0 = mouseX;
    y0 = mouseY;
}

function mouseDragged() {
    x1 = mouseX;
    y1 = mouseY;
    background(255);
    noStroke();
    fill('red');
    ellipse(x0 - 3, y0 - 3, 6);
    fill('green');
    ellipse(x1 - 3, y1 - 3, 6);
}

function mouseReleased() {
    background(255);
    loadPixels();
    drawLine();
    updatePixels();
}

function drawLine() {
    let dx = x1 - x0;
    let dy = y1 - y0;

    let deq = 2 * dy;
    let d = deq - dx;
    let dinc = d - dx;
    let y = y0;

    for (x = x0; x < x1; x++) {
        setPixelColor(x, y, 0);
        if (d < 0) {
            d += deq;
        } else {
            d += dinc;
            y++;
        }
    }
}

function setPixelColor(x, y, color) {
    let idx = Math.round((y * width + x) * 4);
    pixels[idx] = color;
    pixels[idx + 1] = color;
    pixels[idx + 2] = color;
    pixels[idx + 3] = 255;
}