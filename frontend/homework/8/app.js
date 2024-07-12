var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var _this = this;
var express = require('express');
var http = require('http');
var axios = require('axios');
var app = express();
// app.get('/', (req: any, res: any) => {
//     let item = recipesList[0][1][0];
//     console.log("printing recipe name");
//     res.send(item.name);
//     // console.log(recipesList.length);
//     // console.log(recipesList[0])
//     // let arr: any[] = recipesList[0];
//     // let arr1: any[] = arr[1];
//     // let items: any[] = arr1[0];
//     // res.send(items);
//     // res.send('Hello World!');
// });
var recipesList = [];
var arr = [];
app.get('/recipes', function (req, res) { return __awaiter(_this, void 0, void 0, function () {
    var response, error_1;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                _a.trys.push([0, 2, , 3]);
                return [4 /*yield*/, axios.get('https://dummyjson.com/recipes')];
            case 1:
                response = _a.sent();
                // console.log(response.data);
                recipesList = Object.entries(response.data);
                // console.log(typeof response.data);
                // console.log("printing recipes");
                // console.log(recipesList);
                res.json(response.data);
                return [3 /*break*/, 3];
            case 2:
                error_1 = _a.sent();
                console.error('Error fetching data:', error_1);
                res.status(500).send('Error fetching data');
                return [3 /*break*/, 3];
            case 3: return [2 /*return*/];
        }
    });
}); });
app.get('/recipes/all', function (req, res) {
    var items = recipesList[0][1];
    console.log("items details : ");
    // let currItem : Ireceipe;
    items.forEach(function (item) {
        console.log("fetching item : ");
        console.log(item.name);
        var currItem = {
            image: item.image,
            name: item.name,
            rating: item.rating,
            cuisine: item.cuisine,
            ingredients: item.ingredients,
            difficulty: item.difficulty,
            timeTaken: item.prepTimeMinutes + item.cookTimeMinutes,
            calorieCount: item.caloriesPerServing
        };
        arr.push(currItem);
    });
    console.log(arr);
    res.json(arr);
});
app.get('/recipes/:name', function (req, res) { return __awaiter(_this, void 0, void 0, function () {
    var recipeName, response, error_2;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                _a.trys.push([0, 2, , 3]);
                recipeName = req.params.name;
                return [4 /*yield*/, axios.get("https://dummyjson.com/recipes/search?q=".concat(recipeName))];
            case 1:
                response = _a.sent();
                console.log(response.data);
                res.json(response.data);
                return [3 /*break*/, 3];
            case 2:
                error_2 = _a.sent();
                console.error('Error fetching data:', error_2);
                return [3 /*break*/, 3];
            case 3: return [2 /*return*/];
        }
    });
}); });
app.listen(5000, function () {
    console.log('Example app listening on port 5000!');
});
