// Signup functionality
document.addEventListener('DOMContentLoaded', function() {
    const signupForm = document.getElementById('signupForm');

    // Handle form submission
    signupForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const fullName = document.getElementById('fullName').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const country = document.getElementById('country').value;
        const agreeTerms = document.getElementById('agreeTerms').checked;

        // Validation
        if (!fullName || !email || !password || !confirmPassword || !country) {
            alert('Please fill in all fields');
            return;
        }

        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }

        if (password.length < 6) {
            alert('Password must be at least 6 characters long');
            return;
        }

        if (!agreeTerms) {
            alert('Please agree to the terms and conditions');
            return;
        }

        // Check if user already exists
        const users = JSON.parse(localStorage.getItem('users') || '[]');
        const existingUser = users.find(u => u.email === email);

        if (existingUser) {
            alert('User with this email already exists');
            return;
        }

        // Create new user
        const newUser = {
            id: Date.now(),
            fullName,
            email,
            password,
            country,
            createdDate: new Date().toISOString(),
            plasticUsage: {
                daily: 0,
                monthly: 0,
                yearly: 0
            }
        };

        // Save user
        users.push(newUser);
        localStorage.setItem('users', JSON.stringify(users));
        localStorage.setItem('currentUser', JSON.stringify(newUser));

        // Redirect to dashboard
        window.location.href = 'dashboard.html';
    });
});