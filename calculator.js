// Calculator functionality
document.addEventListener('DOMContentLoaded', function() {
    // Check if user is logged in
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!currentUser) {
        window.location.href = 'login.html';
        return;
    }

    const plasticForm = document.getElementById('plasticForm');
    const resultsDiv = document.getElementById('results');

    // Logout functionality
    document.getElementById('logout').addEventListener('click', function() {
        localStorage.removeItem('currentUser');
        window.location.href = 'index.html';
    });

    // Handle form submission
    plasticForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const bottles = parseInt(document.getElementById('bottles').value) || 0;
        const bags = parseInt(document.getElementById('bags').value) || 0;
        const straws = parseInt(document.getElementById('straws').value) || 0;
        const containers = parseInt(document.getElementById('containers').value) || 0;
        const cups = parseInt(document.getElementById('cups').value) || 0;

        // Calculate usage
        const daily = bottles + bags + straws + containers + cups;
        const monthly = daily * 30;
        const yearly = daily * 365;

        // Update results
        document.getElementById('dailyResult').textContent = daily + ' items';
        document.getElementById('monthlyResult').textContent = monthly + ' items';
        document.getElementById('yearlyResult').textContent = yearly + ' items';

        // Calculate environmental impact
        const marineAnimals = Math.ceil(yearly / 1000);
        let impactText = `You use approximately ${yearly} plastic items per year. `;
        impactText += `This could potentially affect ${marineAnimals} marine animal${marineAnimals !== 1 ? 's' : ''}.`;

        document.getElementById('impactText').textContent = impactText;

        // Update progress bar
        const progressFill = document.getElementById('progressFill');
        const progressPercentage = Math.min((daily / 10) * 100, 100); // Max at 10 items per day
        progressFill.style.width = progressPercentage + '%';

        // Show results
        resultsDiv.style.display = 'block';

        // Save to user data
        currentUser.plasticUsage = { daily, monthly, yearly };
        localStorage.setItem('currentUser', JSON.stringify(currentUser));

        // Update users array
        const users = JSON.parse(localStorage.getItem('users') || '[]');
        const userIndex = users.findIndex(u => u.id === currentUser.id);
        if (userIndex !== -1) {
            users[userIndex] = currentUser;
            localStorage.setItem('users', JSON.stringify(users));
        }

        // Scroll to results
        resultsDiv.scrollIntoView({ behavior: 'smooth' });
    });
});