function preload() {
    img = loadImage("https://raw.githubusercontent.com/scikit-image/scikit-image/master/skimage/data/astronaut.png");
    img_h = createImage(256, 256);
    img_s = createImage(256, 256);
    img_v = createImage(256, 256);
}

function setup() {
    createCanvas(512, 512);
    img.resize(256, 256);
    img.loadPixels();
    for (x = 0; x < img.width; x++) {
        for (y = 0; y < img.height; y++) {
            pos = 4 * (y * img.width + x);
            r = img.pixels[pos] / 255;
            g = img.pixels[pos + 1] / 255;
            b = img.pixels[pos + 2] / 255;
            //img.pixels[pos+3];

            cmax = Math.max(r, g, b);
            cmin = Math.min(r, g, b);
            v = cmax;
            c = cmax - cmin;
            s = c / cmax;


            if (c == 0)
                h = 0;
            else if (v == r)
                h = ((g - b) / c) % 6;
            else if (v == g)
                h = ((b - r) / c) + 2;
            else /*v==b*/
                h = ((r - g) / c) + 4;

            h /= 6;
            if (h < 0) h += 1;
            img_h.set(x, y, 255 * h);
            img_s.set(x, y, 255 * s);
            img_v.set(x, y, 255 * v);
        }
    }
    img_h.updatePixels();
    img_s.updatePixels();
    img_v.updatePixels();
    image(img_h, 0, 0);
    image(img_s, 256, 0);
    image(img_v, 0, 256);
    image(img, 256, 256);
}