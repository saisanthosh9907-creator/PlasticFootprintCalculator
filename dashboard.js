// Dashboard functionality
document.addEventListener('DOMContentLoaded', function() {
    // Check if user is logged in
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (!currentUser) {
        window.location.href = 'login.html';
        return;
    }

    // Load user data
    loadDashboardData();

    // Logout functionality
    document.getElementById('logout').addEventListener('click', function() {
        localStorage.removeItem('currentUser');
        window.location.href = 'index.html';
    });

    function loadDashboardData() {
        const plasticUsage = currentUser.plasticUsage || { daily: 0, monthly: 0, yearly: 0 };

        document.getElementById('dailyUsage').textContent = plasticUsage.daily + ' items';
        document.getElementById('monthlyUsage').textContent = plasticUsage.monthly + ' items';
        document.getElementById('yearlyUsage').textContent = plasticUsage.yearly + ' items';

        // Calculate eco score
        const ecoScore = calculateEcoScore(plasticUsage.daily);
        document.getElementById('ecoScore').textContent = ecoScore;

        // Create chart
        createUsageChart(plasticUsage);
    }

    function calculateEcoScore(dailyUsage) {
        if (dailyUsage === 0) return 'Perfect (Green)';
        if (dailyUsage <= 2) return 'Good (Green)';
        if (dailyUsage <= 5) return 'Fair (Yellow)';
        return 'Needs Improvement (Red)';
    }

    function createUsageChart(usage) {
        const ctx = document.getElementById('usageChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Daily', 'Monthly', 'Yearly'],
                datasets: [{
                    label: 'Plastic Items',
                    data: [usage.daily, usage.monthly, usage.yearly],
                    backgroundColor: [
                        'rgba(76, 175, 80, 0.6)',
                        'rgba(255, 152, 0, 0.6)',
                        'rgba(244, 67, 54, 0.6)'
                    ],
                    borderColor: [
                        'rgba(76, 175, 80, 1)',
                        'rgba(255, 152, 0, 1)',
                        'rgba(244, 67, 54, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
});