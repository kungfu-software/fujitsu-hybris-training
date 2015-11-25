<style>
    div.hybris-result  {
        border: 5px dotted green;
        padding: 20px;
    }

    div h3 {
        color: rgb(42, 173, 42);
    }

    div.sap-integration-result  {
        padding: 20px;
        border: 5px dotted #0000ff;
    }
</style>

<div>
    <div class="hybris-result">
        <h3><strong>Congratulations</strong>, Business Partner has been created successfully</h3>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Name:</strong> ${name}</p>
    </div>

    <hr>

    <div class="sap-integration-result">
        <h3>Sap Integration</h3>
        <p><strong>Sap Response:</strong> ${sapResponse}</p>
    </div>
</div>