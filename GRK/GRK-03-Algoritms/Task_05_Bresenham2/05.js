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
    let dx = Math.abs(x1 - x0);
    let dy = Math.abs(y1 - y0);

    let flag = false;
    let tmp;
    if (dx < dy) {
        flag = true;
        tmp = x0;
        x0 = y0;
        y0 = tmp;
        tmp = x1;
        x1 = y1;
        y1 = tmp;
        tmp = dx;
        dx = dy;
        dy = tmp;
    }

    let deq = 2 * dy;
    let d = deq - dx;
    let dinc = d - dx;
    let y = y0;

    let stepX = 0;
    if (x0 < x1) {
        stepX = 1;
    } else if (x1 < x0) {
        stepX = -1;
    } else {
        return;
    }

    let stepY = 0;
    if (y0 < y1) {
        stepY = 1;
    } else if (y1 < y0) {
        stepY = -1;
    } else {
        return;
    }

    for (x = x0; x != x1; x += stepX) {
        if (flag) {
            setPixelColor(y, x, 0);
        } else {
            setPixelColor(x, y, 0);
        }

        if (d < 0) {
            d += deq;
        } else {
            d += dinc;
            y += stepY;
        }
    }
}

function setPixelColor(x, y, color) {
    let idx = (y * width + x) * 4;
    pixels[idx] = color;
    pixels[idx + 1] = color;
    pixels[idx + 2] = color;
    pixels[idx + 3] = 255;
}