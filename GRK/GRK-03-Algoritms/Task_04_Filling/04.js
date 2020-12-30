function setup() {
    createCanvas(512, 512);
    background(255);
}

var last_x = -1;
var last_y = -1;

function mouseDragged() {
    if (mouseButton !== LEFT) return;
    if (last_x > 0) {
        line(last_x, last_y, mouseX, mouseY);
    }
    last_x = mouseX;
    last_y = mouseY;
}

function mouseReleased() {
    last_x = last_y = -1;
    if (mouseButton === RIGHT) {
        loadPixels();
        flood_fill(mouseX, mouseY);
        updatePixels();
    }
}

function set_pixel(x, y, c) {
    idx = (y * width + x) * 4;
    pixels[idx] = c;
    pixels[idx + 1] = c;
    pixels[idx + 2] = c;
    pixels[idx + 3] = 255;
}

function get_pixel(x, y) {
    let idx = (y * width + x) * 4;
    return pixels[idx];
}

function flood_fill(x, y) {
    let stos = [];
    stos.push([x, y]);
    while (stos.length > 0) {
        [x, y] = stos.pop();
        if (x < 0 || width < x || y < 0 || height < y) {
            continue;
        }
        if (get_pixel(x, y) !== 255) {
            continue;
        }
        set_pixel(x, y, 0);
        stos.push([x, y - 1]);
        stos.push([x, y + 1]);
        stos.push([x - 1, y]);
        stos.push([x + 1, y]);
    }
}