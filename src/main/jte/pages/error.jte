@param String errorMessage = null

@template.layout.default(content = @`
    <div class="min-h-screen flex items-center justify-center">
        <div class="max-w-md w-full space-y-8 p-8 bg-white rounded-lg shadow-md">
            <h1 class="text-4xl font-bold text-center">Error</h1>
            <div class="flex justify-center" style="display: block;">
                <h2 class="text-3xl">Something went wrong!</h2>
                <h3 class="text-2xl">Http Error Code: 500. Internal Server Error</h3><br>
            </div>
            <div class="flex justify-center">
                <a href="/" class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                    Home
                </a>
            </div>
        </div>
    </div>
`)