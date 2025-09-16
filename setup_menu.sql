-- Create the categories table
CREATE TABLE IF NOT EXISTS categories (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL
);

-- Create the menu_items table
CREATE TABLE IF NOT EXISTS menu_items (
    id INTEGER PRIMARY KEY,
    parent_id INTEGER,
    lft INTEGER NOT NULL,
    rgt INTEGER NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    tags TEXT, -- JSON or comma-separated tags
    price REAL,
    discount REAL DEFAULT 0,
    category_id INTEGER,
    FOREIGN KEY (parent_id) REFERENCES menu_items(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Insert sample category data
INSERT INTO categories (id, name) VALUES
(1, 'Main Dish'),
(2, 'Side'),
(3, 'Drink'),
(4, 'Add-on');

-- Insert sample menu items
INSERT INTO menu_items (id, parent_id, lft, rgt, name, description, tags, price, discount, category_id) VALUES
(1, NULL, 1, 14, 'Burger Combo', 'A complete burger meal', 'combo,popular', 15.00, 0, 1),
(2, 1, 2, 3, 'Cheeseburger', 'Beef patty, cheese & lettuce', 'beef,cheese', 10.00, 0, 1),
(3, 1, 4, 5, 'Fries (Medium)', 'Medium-sized fries', 'side,veg', 3.00, 0, 2),
(4, 1, 6, 7, 'Cola (Can)', '330ml soft drink', 'drink', 2.00, 0, 3),
(5, NULL, 15, 16, 'Chicken Burger', 'Grilled chicken breast', 'chicken,spicy', 9.50, 10, 1),
(6, NULL, 17, 18, 'Extra Cheese', 'Add-on cheese slice', 'addon,cheese', 1.50, 0, 4),
(7, NULL, 19, 20, 'Fries (Large)', 'Large portion of fries', 'side,veg,popular', 4.50, 5, 2),
(8, NULL, 21, 22, 'Chocolate Shake', 'Thick chocolate milkshake', 'drink,dessert', 5.00, 0, 3);
