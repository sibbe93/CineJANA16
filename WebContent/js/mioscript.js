$(document).ready(function(){
    $(document).on("click", ".espandi", function(event){
        event.preventDefault()
        $(this).parent().children(".more").addClass("d-none")
        $(this).parent().children(".content").removeClass("d-none");
        $(this).removeClass("espandi").addClass("meno").text("meno")
    })

    $(document).on("click", ".meno", function(event){
        event.preventDefault()
        $(this).parent().children(".more").removeClass("d-none")
        $(this).parent().children(".content").addClass("d-none");
        $(this).removeClass("meno").addClass("espandi").text("altro")
    })

    $(document).on("click", ".dropdown-mio", function(){
        $(this).toggleClass("menu")
    })
 
    $(document).on("click", function(){
     $(".dropdown-mio").removeClass("menu")
    })
})

