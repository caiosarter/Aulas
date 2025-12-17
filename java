const urlCSV = "https://docs.google.com/spreadsheets/d/e/2PACX-1vResfZtKfATsuGT-EgsyFoPaezv8XxIBx_PRpQRd-U3-uyIG2bQzA_-zmp9QEUhz2S6361yOTzsf6h9/pub?gid=373547015&single=true&output=csv";

fetch(urlCSV)
    .then(response => response.text())
    .then(data => {
        const linhas = data.split("\n").slice(1);

        let atividade = [];
        let frutas = [];
        let sono = [];

        linhas.forEach(linha => {
            const colunas = linha.split(",");

            atividade.push(Number(colunas[0]));
            frutas.push(Number(colunas[1]));
            sono.push(Number(colunas[3]));
        });

        criarGrafico("atividadeFisica", atividade, "Dias por semana");
        criarGrafico("frutas", frutas, "Porções por semana");
        criarGrafico("sono", sono, "Horas de sono");
    });

function criarGrafico(id, dados, label) {
    new Chart(document.getElementById(id), {
        type: "bar",
        data: {
            labels: dados.map((_, i) => i + 1),
            datasets: [{
                label: label,
                data: dados,
                backgroundColor: "#4CAF50"
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
