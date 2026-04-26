-- Seed data for development/testing
-- Insert sample seller user
INSERT INTO users (name, email, password_hash, contact, address, role)
VALUES (
  'Seller One',
  'seller@gmail.com',
  '$2a$10$xDqpjpCWnuDdMDKXP7VPdOslFxYHLDL2TgKXO6MzAQvKXjPZxNWyq', -- bcrypt hash of 'seller123'
  '9876543210',
  '123 Seller Street, Kathmandu',
  'SELLER'
) ON CONFLICT (email) DO NOTHING;

-- Insert sample bidder user
INSERT INTO users (name, email, password_hash, contact, address, role)
VALUES (
  'Bidder One',
  'bidder@gmail.com',
  '$2a$10$MJUPrvQU1hCr3Ll5X8NZ3e6G5O5Q5Rf8E0H3KZL1vN3M2Y0QvvJkO', -- bcrypt hash of 'bidder123'
  '9123456789',
  '456 Bidder Avenue, Kathmandu',
  'BIDDER'
) ON CONFLICT (email) DO NOTHING;

-- Insert sample product listed by seller
INSERT INTO products (name, description, entry_time, expiry_time, min_bidding_amount)
VALUES (
  'Vintage Camera',
  'A beautiful vintage film camera in working condition',
  '2026-04-21 10:00',
  '2026-04-28 18:00',
  5000
) ON CONFLICT (name) DO NOTHING;

