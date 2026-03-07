// Profile functionality
document.addEventListener('DOMContentLoaded', function() {
    // Check if user is logged in
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!currentUser) {
        window.location.href = 'login.html';
        return;
    }

    const editBtn = document.getElementById('editBtn');
    const saveBtn = document.getElementById('saveBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    const passwordForm = document.getElementById('passwordForm');
    const changePhotoBtn = document.getElementById('changePhoto');
    const photoUpload = document.getElementById('photoUpload');
    const profileImg = document.getElementById('profileImg');

    // Load user data
    loadProfileData();

    // Logout functionality
    document.getElementById('logout').addEventListener('click', function() {
        localStorage.removeItem('currentUser');
        window.location.href = 'index.html';
    });

    // Edit profile
    editBtn.addEventListener('click', function() {
        enableEditing(true);
    });

    // Save changes
    saveBtn.addEventListener('click', function() {
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const country = document.getElementById('country').value;

        if (!name || !email || !country) {
            alert('Please fill in all fields');
            return;
        }

        // Update user data
        currentUser.fullName = name;
        currentUser.email = email;
        currentUser.country = country;

        // Update localStorage
        localStorage.setItem('currentUser', JSON.stringify(currentUser));

        // Update users array
        const users = JSON.parse(localStorage.getItem('users') || '[]');
        const userIndex = users.findIndex(u => u.id === currentUser.id);
        if (userIndex !== -1) {
            users[userIndex] = currentUser;
            localStorage.setItem('users', JSON.stringify(users));
        }

        enableEditing(false);
        alert('Profile updated successfully!');
    });

    // Cancel editing
    cancelBtn.addEventListener('click', function() {
        loadProfileData();
        enableEditing(false);
    });

    // Change photo
    changePhotoBtn.addEventListener('click', function() {
        photoUpload.click();
    });

    photoUpload.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                profileImg.src = e.target.result;
                currentUser.profilePhoto = e.target.result;
                localStorage.setItem('currentUser', JSON.stringify(currentUser));
            };
            reader.readAsDataURL(file);
        }
    });

    // Change password
    passwordForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const currentPassword = document.getElementById('currentPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmNewPassword = document.getElementById('confirmNewPassword').value;

        if (currentPassword !== currentUser.password) {
            alert('Current password is incorrect');
            return;
        }

        if (newPassword !== confirmNewPassword) {
            alert('New passwords do not match');
            return;
        }

        if (newPassword.length < 6) {
            alert('New password must be at least 6 characters long');
            return;
        }

        // Update password
        currentUser.password = newPassword;

        // Update localStorage
        localStorage.setItem('currentUser', JSON.stringify(currentUser));

        // Update users array
        const users = JSON.parse(localStorage.getItem('users') || '[]');
        const userIndex = users.findIndex(u => u.id === currentUser.id);
        if (userIndex !== -1) {
            users[userIndex] = currentUser;
            localStorage.setItem('users', JSON.stringify(users));
        }

        alert('Password changed successfully!');
        passwordForm.reset();
    });

    function loadProfileData() {
        document.getElementById('name').value = currentUser.fullName;
        document.getElementById('email').value = currentUser.email;
        document.getElementById('country').value = currentUser.country;
        document.getElementById('createdDate').value = new Date(currentUser.createdDate).toLocaleDateString();

        if (currentUser.profilePhoto) {
            profileImg.src = currentUser.profilePhoto;
        }

        const plasticUsage = currentUser.plasticUsage || { monthly: 0, yearly: 0 };
        document.getElementById('monthlyUsage').textContent = plasticUsage.monthly + ' items';
        document.getElementById('yearlyUsage').textContent = plasticUsage.yearly + ' items';

        const ecoScore = calculateEcoScore(plasticUsage.daily || 0);
        document.getElementById('ecoScore').textContent = ecoScore;
    }

    function enableEditing(enable) {
        const inputs = ['name', 'email', 'country'];
        inputs.forEach(id => {
            document.getElementById(id).readOnly = !enable;
        });

        editBtn.style.display = enable ? 'none' : 'inline-block';
        saveBtn.style.display = enable ? 'inline-block' : 'none';
        cancelBtn.style.display = enable ? 'inline-block' : 'none';
    }

    function calculateEcoScore(dailyUsage) {
        if (dailyUsage === 0) return 'Perfect (Green)';
        if (dailyUsage <= 2) return 'Good (Green)';
        if (dailyUsage <= 5) return 'Fair (Yellow)';
        return 'Needs Improvement (Red)';
    }
});