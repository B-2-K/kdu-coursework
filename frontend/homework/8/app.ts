const express = require('express');
const http = require('http');
const axios = require('axios');

const app = express();

// Interface for defining the structure of a recipe
interface Ireceipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    timeTaken: number;
    calorieCount: number;
}

// Array to store fetched recipes and processed recipes
let recipesList: any[] = [];
let arr: Ireceipe[] = [];

// Route to fetch all recipes from an external API
app.get('/recipes', async (req: any, res: any) => {
    try {
        const response = await axios.get('https://dummyjson.com/recipes');
        console.log("all recipes found");
        recipesList = Object.entries(response.data);
        res.json(response.data); 
    } catch (error) {
        console.error('Error fetching data:', error);
        res.status(500).send('Error fetching data');
    }
});

// Route to fetch and process all recipes
app.get('/recipes/all', (req: any, res: any) => {
    let items = recipesList[0][1];
    console.log("items details : ");
    items.forEach((item: any) => {
        console.log("fetching item : ")
        console.log(item.name);
        let currItem: Ireceipe = {
            image: item.image,
            name: item.name,
            rating: item.rating,
            cuisine: item.cuisine,
            ingredients: item.ingredients,
            difficulty: item.difficulty,
            timeTaken: item.prepTimeMinutes + item.cookTimeMinutes,
            calorieCount: item.caloriesPerServing
        }
        arr.push(currItem);
    })
    console.log(arr);
    res.json(arr);
})

// Route to search for a recipe by name
app.get('/recipes/:name', async (req: any, res: any) => {
    try {
        const recipeName = req.params.name;
        const response = await axios.get(`https://dummyjson.com/recipes/search?q=${recipeName}`);
        console.log("printting searched data");
        res.json(response.data);
    }
    catch (error) {
        console.error('Error fetching data:', error);
    }
});

app.listen(5000, () => {
    console.log('Example app listening on port 5000!');
});