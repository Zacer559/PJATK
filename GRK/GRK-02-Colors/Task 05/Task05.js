function preload() {
    img = loadImage("astronaut.png");
    img_h = createImage(256, 256);
    img_s = createImage(256, 256);
    img_v = createImage(256, 256);
}

function setup() {
    createCanvas(256, 256);
    img.filter('gray');
    var Histogram = new Array(256);
    Histogram.fill(0);
    img.loadPixels();
    for (x = 0; x < img.width; x++) {
        for (y = 0; y < img.height; y++) {
            pos = 4 * (x * img.width + y);
            value = img.pixels[pos + 1];
            Histogram[value] += 1;
        }
    }
    console.log(Histogram)
    background(0);
    stroke('white');
    //There are more than 29000 black(value 0) pixels, so im removing them to get better graph scaling
    Histogram[0] = 0;
    for (x = 0; x < 256; x++) {
        line(x, 0, x, 256 - (Histogram[x] / Math.max(...Histogram) * 256))
    }

    img.updatePixels();
}