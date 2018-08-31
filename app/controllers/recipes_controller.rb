class RecipesController < ApplicationController
  def index
    recipes = Recipe.all.select(:name, :description)
    render json: recipes
  end
end
