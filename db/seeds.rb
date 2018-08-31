# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

recipe_info = {
  "Salisbury Steak" => "Salisbury Steak is a dish made from a blend of ground beef and other ingredients and is usually served with gravy or brown sauce. Hamburg steak is a similar product but differs in ingredients.",
  "Bangers and Mash" => "Bangers and mash, also known as sausages and mash, is a traditional dish of Great Britain and Ireland comprising sausages served with mashed potatoes. It may consist of one of a variety of flavoured sausages made of pork, lamb, or beef.",
  "Beef Wellington" => "Beef Wellington is a preparation of fillet steak coated with pâté and duxelles, which is then wrapped in puff pastry and baked.",
  "Laksa" => "Laksa is a spicy noodle soup popular in the Peranakan cuisine. Laksa consists of rice noodles or rice vermicelli with chicken, prawn or fish, served in spicy soup based on either rich and spicy curry coconut milk or on sour asam.",
  "Pho" => "Phở or pho is a Vietnamese soup consisting of broth, rice noodles called bánh phở, a few herbs, and meat, primarily made with either beef or chicken.",
  "sample" => "random"
}

recipe_info.each do |name, description|
  Recipe.find_or_create_by(name: name) do |recipe|
    recipe.description = description
  end
end
