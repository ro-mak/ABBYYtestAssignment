package ru.makproductions.abbyytestassignment.model.entity

import android.graphics.Bitmap

class Cat(val name: String, val image: Bitmap)

enum class Cats(val catName: String, val path: String) {
    Barsik("Барсик", "http://pngimg.com/uploads/cat/cat_PNG50546.png"),
    Kasis("Кэсис", "http://pngimg.com/uploads/cat/cat_PNG50537.png"),
    Ferrucio("Ферруцио", "http://pngimg.com/uploads/cat/cat_PNG50525.png"),
    Clemente("Клементе", "http://pngimg.com/uploads/cat/cat_PNG50511.png"),
    Grumpy("Грампи", "http://pngimg.com/uploads/cat/cat_PNG50498.png"),
    Yuppi("Юппи", "http://pngimg.com/uploads/cat/cat_PNG50480.png"),
    Shelly("Шелли", "http://pngimg.com/uploads/cat/cat_PNG50433.png"),
    Cactus("Кактус", "http://pngimg.com/uploads/cat/cat_PNG50425.png"),
    Leopardo("Леопардо", "http://pngimg.com/uploads/cat/cat_PNG120.png"),
    Zhulya("Жуля", "http://pngimg.com/uploads/cat/cat_PNG104.png");
}